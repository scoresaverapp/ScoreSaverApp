package com.scoresaver.app.wear.features.user_settings.ui

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
import com.scoresaver.app.util.Darkgrey
import com.scoresaver.app.util.LightGrey
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.features.new_game.presentation.NewGameViewModel
import com.scoresaver.app.wear.navigation.Screen
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.FullWidthRoundButton
import com.scoresaver.core_ui.components.buttons.FullWidthRoundSwitchButton
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.app.wear.components.typography.CustomText

@Composable
internal fun GenderScreen(
    navController: NavController,
    viewModel: NewGameViewModel
) {

    val male = viewModel.getMaleSwitchValue()
    val female = viewModel.getFemaleSwitchValue()
    val buttonEnabled = male.switchValue || female.switchValue

    fun getStartGameButtonBackgroundColor() = if (buttonEnabled) Orange else Black
    fun getStartGameButtonBorderColor() = if (buttonEnabled) Orange else Darkgrey
    fun getStartGameTextColor() = if (buttonEnabled) White else Darkgrey

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 30
        )
    }

    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 2),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart

        ) {
            item {
                CustomText(
                    text = stringResource(id = R.string.gender_title),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange,
                    )
                )
            }
            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.male),
                    checked = male.switchValue,
                    onClick = viewModel::setMaleSwitchValue
                )
            }

            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.female),
                    checked = female.switchValue,
                    onClick = viewModel::setFemaleSwitchValue
                )
            }
            item {
                CustomSpacer(size = 8.dp)
            }

            item {
                Row(modifier = Modifier.padding(horizontal = 38.dp)) {
                    FullWidthRoundButton(
                        text = stringResource(id = R.string.next_step),
                        onPress = { navController.navigate(Screen.AgeScreen.route) },
                        backgroundColor = getStartGameButtonBackgroundColor(),
                        borderColor = getStartGameButtonBorderColor(),
                        textColor = getStartGameTextColor(),
                        textAlign = TextAlign.Center,
                        enable = male.switchValue || female.switchValue
                    )
                }
            }
        }
    }
}

