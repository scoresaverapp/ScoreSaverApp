package com.scoresaver.app.wear.features.user_settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.features.new_game.presentation.NewGameViewModel
import com.scoresaver.app.wear.navigation.Screen
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.FullWidthRoundButton
import com.scoresaver.app.wear.components.typography.CustomText

@Composable
internal fun HeightScreen(
    navController: NavController,
    viewModel: NewGameViewModel
) {

    val height = viewModel.getHeightValue()

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 2,
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
                    text = stringResource(id = R.string.height_title),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange,
                    )
                )
            }
            item {
                CustomText(
                    text = stringResource(id = R.string.height_question),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = White,
                    )
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(
                        modifier = Modifier.clickable {
                            viewModel.setHeightValue(height + 1)
                        },
                        text = "+",
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                        )
                    )

                    CustomText(
                        text = height.toString(),
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                        )
                    )
                    CustomText(
                        modifier = Modifier.clickable {
                            viewModel.setHeightValue(height - 1)
                        },
                        text = "-",
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                        )
                    )
                }
            }

            item {
                CustomText(
                    text = stringResource(id = R.string.cm),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = White,
                    )
                )
            }

            item {
                Row(modifier = Modifier.padding(horizontal = 38.dp)) {
                    FullWidthRoundButton(
                        text = stringResource(id = R.string.next_step),
                        onPress = {
                            navController.navigate(Screen.WeightScreen.route)
                        },
                        backgroundColor = Orange,
                        borderColor = Orange,
                        textColor = Black,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}