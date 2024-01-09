package com.scoresaver.game_ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.scoresaver.app.R
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.Green
import com.scoresaver.app.util.Grey
import com.scoresaver.app.util.LightBlack
import com.scoresaver.app.util.LightRed
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.features.game.presentation.GameViewModel
import com.scoresaver.app.wear.features.game.presentation.ui.components.StatsValue
import com.scoresaver.core_ui.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.core_ui.components.typography.CustomText
import kotlinx.coroutines.delay

@Composable
internal fun TimeAndCaloriesScreen(viewModel: GameViewModel) {

    val isSingleMatch = viewModel.isSingleMatch
    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 140
        )
    }

    if (viewModel.showSnackbar) {
        ShowSnackbar(
            viewModel = viewModel,
            message = if (viewModel.isKillerPointActive)
                stringResource(id = R.string.killer_point_active)
            else {
                stringResource(id = R.string.killer_point_deactive)
            }
        )
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
                    if (!isSingleMatch) {
                        RoundButton(
                            size = 40.5.dp,
                            icon = R.drawable.ic_tennis_ball,
                            iconColor = White,
                            iconSize = 16.5.dp,
                            backgroundColor = Black,
                            borderBackground = White,
                            isDoubleMatch = true,
                            onClick = { })
                        CustomSpacer(size = 4.dp, horizontal = true)
                    }
                    if(!isTieBreak) {
                        RoundButton(
                            size = 40.5.dp,
                            backgroundColor = if (viewModel.isKillerPointActive) Orange else Grey,
                            borderBackground = if (viewModel.isKillerPointActive) Orange else Grey,
                            icon = R.drawable.ic_poison,
                            iconColor = Black,
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
                            viewModel.stopTimer()
                            viewModel.stopHeartRateListener()
                        })

                    CustomSpacer(size = 27.dp, horizontal = true)

                    RoundButton(
                        size = 46.dp,
                        backgroundColor = Green,
                        icon = R.drawable.ic_play,
                        iconColor = Black,
                        iconSize = 18.5.dp,
                        onClick = {
                            viewModel.startTimer()
                            viewModel.startHeartRateListener()
                        })
                }
            }
        }
    }
}

@Composable
internal fun ShowSnackbar(
    viewModel: GameViewModel,
    message: String
) {
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    LaunchedEffect(snackbarVisibleState) {
        // Show the Snackbar
        setSnackBarState(true)
        // Delay for 2 seconds
        delay(500)
        viewModel.hideSnackBar()
        setSnackBarState(false)
    }

    if (snackbarVisibleState) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, LightBlack, shape = RoundedCornerShape(8.dp))
                .background(LightBlack)
                .padding(horizontal = 24.dp, 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomImageVectorIcon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning),
                    color = LightRed,
                    contentDescription = ""
                )
                CustomSpacer(size = 8.dp, horizontal = true)
                CustomText(
                    text = message,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = White,
                    )
                )

            }
        }
    }
}

