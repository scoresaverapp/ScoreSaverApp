package com.scoresaver.app.wear.features.user_settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.scoresaver.app.util.LightBlack
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.app.wear.components.typography.CustomText
import com.scoresaver.app.wear.features.new_game.presentation.ViewModelFragment
import com.scoresaver.app.wear.navigation.Screen
import com.scoresaver.app.wear.components.buttons.FullWidthRoundButton
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer

@Composable
internal fun SportScreen(
    navController: NavController,
    viewModel: ViewModelFragment.NewGame
) {

    val sport = viewModel.getSportType()
    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 1,
            initialCenterItemScrollOffset = 40
        )
    }

    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 2),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            item {
                CustomText(
                    text = stringResource(id = R.string.new_game),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange
                    )
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    RoundButton(
                        textColor = White,
                        size = 50.dp,
                        backgroundColor = if (sport == 1) Orange else LightBlack,
                        showOnlyText = true,
                        titleButton = stringResource(id = R.string.padel),

                        onClick = {
                            viewModel.setSportType(1)
                        })
                    CustomSpacer(size = 32.5.dp, horizontal = true)
                    RoundButton(
                        textColor = White,
                        size = 50.dp,
                        backgroundColor = if (sport == 2) Orange else LightBlack,
                        showOnlyText = true,
                        titleButton = stringResource(id = R.string.tennis),
                        onClick = {
                            viewModel.setSportType(2)
                        })
                }
            }
            item {
                CustomSpacer(size = 32.dp)
            }

            item {
                Row(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FullWidthRoundButton(
                        text = stringResource(id = R.string.next_step),
                        onPress = {
                            navController.navigate(Screen.NewGameScreen.route)
                        },
                        backgroundColor = Orange,
                        borderColor = Orange,
                        textColor = White,
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }
}