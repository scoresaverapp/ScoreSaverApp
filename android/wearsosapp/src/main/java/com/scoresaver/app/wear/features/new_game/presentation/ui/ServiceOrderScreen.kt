package com.scoresaver.app.wear.features.new_game.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.scoresaver.app.util.Orange
import com.scoresaver.app.wear.features.new_game.presentation.ViewModelFragment
import com.scoresaver.app.wear.features.new_game.presentation.ui.components.ServiceOrderTeamOne
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.FullWidthRoundSwitchButton
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.app.wear.components.typography.CustomText
import com.scoresaver.app.wear.features.new_game.presentation.ui.components.ServiceOrderTeamTwo

@Composable
internal fun ServiceOrderScreen(viewModel: ViewModelFragment.ServiceOrder) {
    val playerOne = viewModel.getPlayerOneValue()
    val playerTwo = viewModel.getPlayerTwoValue()
    val playerThree = viewModel.getPlayerThreeValue()
    val playerFour = viewModel.getPlayerFourValue()
    val serviceOrderConfirmed = viewModel.getServiceOrderConfirmed()

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0)
    }

    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemOffset = 70),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart
        ) {
            item {
                CustomText(
                    text = stringResource(id = R.string.order_service),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange,
                    )
                )
            }
            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.order),
                    checked = serviceOrderConfirmed,
                    onClick = viewModel::toggleServiceOrderConfirmed
                )
            }
            if(serviceOrderConfirmed) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ServiceOrderTeamOne(
                                playerOne = playerOne,
                                playerTwo = playerTwo,
                                swapTeamOrder = viewModel::swapServiceOrderTeamOne
                            )
                        }
                        CustomSpacer(size = 8.dp, horizontal = true)
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ServiceOrderTeamTwo(
                                playerOne = playerThree,
                                playerTwo = playerFour,
                                swapTeamOrder = viewModel::swapServiceOrderTeamTwo
                            )
                        }
                    }
                }
                item { 
                    CustomSpacer(size = 8.dp)
                }
                item {
                    RoundButton(
                        size = 40.dp,
                        backgroundColor = Orange,
                        icon = R.drawable.ic_opposite_arrows,
                        iconColor = Black,
                        iconSize = 22.dp,
                        onClick = viewModel::swapServiceOrdersBetweenTeams
                    )
                }

            }
        }
    }
}