package com.scoresaver.app.wear.features.game.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
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
import com.scoresaver.app.util.LightRed
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.navigation.Screen
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.core_ui.components.typography.CustomText

@Composable
internal fun CompleteGameScreen(
    navController: NavController
) {
    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 2,
            initialCenterItemScrollOffset = 40
        )
    }

    MyScaffold(scalingLazyState = scalingLazyState, hideTimer = true) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 2),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart
        ) {
            item {
                Row (
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center){
                    CustomText(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = stringResource(id = R.string.complete_game),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            item {
                CustomSpacer(size = 4.dp)
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
                            navController.navigate(Screen.GameScreen.route)
                        })

                    CustomSpacer(size = 27.dp, horizontal = true)

                    RoundButton(
                        size = 46.dp,
                        backgroundColor = Green,
                        icon = R.drawable.ic_check,
                        iconColor = Black,
                        iconSize = 18.5.dp,
                        onClick = {
                           navController.navigate(Screen.ListGameScreen.route) {
                                popUpTo(Screen.GameScreen.route) {
                                    inclusive = true
                                }
                            }
                        })
                }
            }

        }
    }

}