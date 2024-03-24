package com.scoresaver.app.wear.features.new_game.presentation.ui

import android.Manifest
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.scoresaver.app.R
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.Blue
import com.scoresaver.app.util.Darkgrey
import com.scoresaver.app.util.LightBlack
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.util.util.checkPermission
import com.scoresaver.app.util.util.requestPermission
import com.scoresaver.app.wear.features.new_game.presentation.ViewModelFragment
import com.scoresaver.app.wear.navigation.Screen
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.app.wear.components.buttons.FullWidthRoundButton
import com.scoresaver.core_ui.components.lifecycle_observer.lifecycleObserver
import com.scoresaver.app.wear.components.typography.CustomText
import com.scoresaver.app.wear.features.game.presentation.GameViewModel


@Composable
internal fun NewGameScreen(
    navController: NavController,
    viewModel: ViewModelFragment.NewGame,
    gameViewModel: GameViewModel
) {
    val single = viewModel.getSingleGameSwitchValue()
    val double = viewModel.getDoubleGameSwitchValue()
    val advantages = viewModel.getAdvantagesSwitchValue()
    val killerPoint = viewModel.getKillerPointSwitchValue()

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 30
        )
    }

    fun getGameTypeIcon() =
        if (single.switchValue || double.switchValue) R.drawable.ic_check else null

    fun getGameRuleIcon() =
        if (advantages.switchValue || killerPoint.switchValue) R.drawable.ic_check else null

    fun isStartGameButtonEnabled() =
        (single.switchValue && (advantages.switchValue || killerPoint.switchValue)) ||
                (double.switchValue && (advantages.switchValue || killerPoint.switchValue))

    fun getStartGameButtonBackgroundColor() = if (isStartGameButtonEnabled()) Orange else Black
    fun getStartGameButtonBorderColor() = if (isStartGameButtonEnabled()) Orange else Darkgrey
    fun getStartGameTextColor() = if (isStartGameButtonEnabled()) White else Darkgrey

    val permissionResultLauncher = requestPermission(Manifest.permission.BODY_SENSORS) {
        navController.navigate(Screen.GameScreen.route) {
            viewModel.resetData()
            gameViewModel.startTimer()
            popUpTo(Screen.NewGameScreen.route) {
                inclusive = true
            }
        }
    }

    val checkBodySensorPermissionStatus =
        checkPermission(permission = Manifest.permission.BODY_SENSORS)

    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 2),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart
        ) {
            item {
                CustomText(
                    text = stringResource(id = R.string.new_game),
                    textStyle = titleTextStyle()
                )
            }
            item {
                FullWidthRoundButton(
                    text = stringResource(id = R.string.type_game),
                    onPress = { navController.navigate(Screen.GameTypeScreen.route) },
                    backgroundColor = LightBlack,
                    borderColor = LightBlack,
                    textColor = White,
                    rightIcon = getGameTypeIcon(),
                    textSize = 16,
                    iconSize = 12.dp,
                    iconColor = Blue
                )
            }

            item {
                FullWidthRoundButton(
                    text = stringResource(id = R.string.rules_game),
                    onPress = { navController.navigate(Screen.GameRulesScreen.route) },
                    backgroundColor = LightBlack,
                    borderColor = LightBlack,
                    textColor = White,
                    rightIcon = getGameRuleIcon(),
                    textSize = 16,
                    iconSize = 12.dp,
                    iconColor = Blue
                )
            }
            item {
                Row(modifier = Modifier.padding(horizontal = 38.dp)) {
                    FullWidthRoundButton(
                        text = stringResource(id = R.string.start_game),
                        textAlign = TextAlign.Center,
                        onPress = {
                            permissionResultLauncher()
                            viewModel.insertGameSettings()
                        },
                        backgroundColor = getStartGameButtonBackgroundColor(),
                        borderColor = getStartGameButtonBorderColor(),
                        textColor = getStartGameTextColor(),
                        enable = isStartGameButtonEnabled()
                    )
                }
            }
        }
    }
    lifecycleObserver(onResume = {
        checkBodySensorPermissionStatus()
    })
}

private fun titleTextStyle() = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight(400),
    color = Orange,
)