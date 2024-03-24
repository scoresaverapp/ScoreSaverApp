package com.scoresaver.app.wear.features.game.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.scoresaver.app.R
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.Green
import com.scoresaver.app.util.Grey
import com.scoresaver.app.util.LightGrey
import com.scoresaver.app.util.LightRed
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.app.wear.components.dialogs.AlertDialog
import com.scoresaver.app.wear.components.dialogs.ConfirmationAlert
import com.scoresaver.app.wear.features.game.presentation.GameViewModel
import com.scoresaver.app.wear.features.game.presentation.ui.components.StatsValue
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.app.wear.components.typography.CustomText
import com.scoresaver.app.wear.navigation.Screen
import kotlinx.coroutines.delay

@Composable
internal fun TimeAndCaloriesScreen(
    navController: NavController,
    viewModel: GameViewModel
) {

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 140
        )
    }

    if (viewModel.showSnackbar) {
        ShowAlertKillerPoint(
            viewModel = viewModel,
            message = if (viewModel.isKillerPointActive)
                stringResource(id = R.string.killer_point_active)
            else {
                stringResource(id = R.string.killer_point_deactive)
            }
        )
    }

    if (viewModel.actionCloseGame) {
        AlertDialog(
            textTitle = stringResource(id = R.string.complete_game_title),
            textMessage = stringResource(id = R.string.complete_game_description)
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                RoundButton(
                    size = 46.dp,
                    backgroundColor = LightRed,
                    icon = R.drawable.ic_close,
                    iconColor = Black,
                    iconSize = 15.dp,
                    onClick = {
                        navController.navigate(Screen.GameScreen.route)
                        viewModel.setOnClickCloseGame(false)
                    })
                CustomSpacer(size = 16.dp, horizontal = true)
                RoundButton(
                    size = 46.dp,
                    backgroundColor = Green,
                    icon = R.drawable.ic_check,
                    iconColor = Black,
                    iconSize = 18.5.dp,
                    onClick = {
                        viewModel.stopHeartRateListener()
                        viewModel.stopTimer()
                        viewModel.saveResult()
                        navController.navigate(Screen.ListGameScreen.route) {
                            viewModel.resetData()
                            popUpTo(Screen.GameScreen.route) {
                                inclusive = true
                            }
                            popUpTo(Screen.SportScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    })
            }
        }
    }
    val isTieBreak = viewModel.gameTeam1 == "6" && viewModel.gameTeam2 == "6"


    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 1),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart
        ) {
            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    if (!isTieBreak && (viewModel.scoreTeam1 != "A" && viewModel.scoreTeam2 != "A")) {
                        RoundButton(
                            size = 40.5.dp,
                            backgroundColor = if (viewModel.isKillerPointActive) Orange else Grey,
                            borderBackground = if (viewModel.isKillerPointActive) Orange else Grey,
                            icon = R.drawable.ic_poison,
                            iconColor = if (viewModel.isKillerPointActive) White else Black,
                            iconSize = 16.5.dp,
                            onClick = {
                                viewModel.updateKillerPoint()
                            }
                        )

                    }
                }
            }
            item {
                StatsValue(
                    text = stringResource(id = R.string.time),
                    value = viewModel.formattedSeconds,
                    valueColor = Orange
                )
            }
            item {
                StatsValue(
                    text = stringResource(id = R.string.hearth),
                    value = viewModel.hearthRate.toString(),
                    valueColor = Orange
                )
            }
            item {
                StatsValue(
                    text = stringResource(id = R.string.kcal),
                    value = viewModel.calories,
                    valueColor = Orange
                )
            }
            item {
                Row {
                    RoundButton(
                        size = 46.dp,
                        backgroundColor = LightRed,
                        icon = R.drawable.ic_close,
                        iconColor = Black,
                        iconSize = 15.dp,
                        onClick = {
                            viewModel.onClickCloseGame()
                        })

                    CustomSpacer(size = 27.dp, horizontal = true)

                    RoundButton(
                        size = 46.dp,
                        backgroundColor = if (viewModel.isTimerRunning) LightGrey else Green,
                        icon = if (viewModel.isTimerRunning) R.drawable.ic_pause else R.drawable.ic_play,
                        iconColor = Black,
                        iconSize = 18.5.dp,
                        onClick = {
                            if (viewModel.isTimerRunning) {
                                viewModel.stopTimer()
                                viewModel.stopHeartRateListener()
                            } else {
                                viewModel.startHeartRateListener()
                                viewModel.startTimer()
                            }
                        })
                }
            }
        }
    }
}


@Composable
internal fun ShowAlertKillerPoint(
    viewModel: GameViewModel,
    message: String
) {
    val (alertConfirmation, showAlertCofirmation) = remember { mutableStateOf(false) }

    LaunchedEffect(alertConfirmation) {
        showAlertCofirmation(true)
        delay(500)
        viewModel.hideSnackBar()
        showAlertCofirmation(false)
    }

    if (alertConfirmation) {
        ConfirmationAlert(colorIcon = LightRed, icon = R.drawable.ic_warning) {
            CustomText(
                text = message,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                )
            )
        }
    }
}
