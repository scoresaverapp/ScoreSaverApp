package com.scoresaver.game_ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.scoresaver.app.R
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.LightBlue
import com.scoresaver.app.util.LightRed
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.features.game.presentation.GameViewModel
import com.scoresaver.app.wear.features.game.presentation.ui.components.ScoreText
import com.scoresaver.core_ui.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.core_ui.components.layout.Separator

@Composable
internal fun ScoreGameScreen(viewModel: GameViewModel) {

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 0,
            initialCenterItemScrollOffset = 140
        )
    }

    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 1),
            state = scalingLazyState,
            anchorType = ScalingLazyListAnchorType.ItemStart
        ) {
            item {
                ScoreText(
                    currentPointPlayer1 = viewModel.scoreTeam1,
                    currentPointPlayer2 = viewModel.scoreTeam2,
                    numberGamePlayer1 = viewModel.gameTeam1,
                    numberGamePlayer2 = viewModel.gameTeam2,
                    numberSetPlayer1 = viewModel.setTeam1,
                    numberSetPlayer2 = viewModel.setTeam2
                )
            }

            item {
                Separator(backgroundColor = White, paddingLeft = 16.dp, paddingRight = 16.dp)
            }

            item {
                Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.padding(top = 4.dp)) {
                    RoundButton(
                        size = 43.dp,
                        backgroundColor = Orange,
                        showOnlyText = true,
                        titleButton = stringResource(id = R.string.team_1),

                        onClick = {
                            viewModel.addPointsTeam1()
                        })
                    CustomSpacer(size = 32.5.dp, horizontal = true)
                    RoundButton(
                        size = 43.dp,
                        backgroundColor = LightBlue,
                        showOnlyText = true,
                        titleButton = stringResource(id = R.string.team_2),

                        onClick = {
                            viewModel.addPointsTeam2()
                        })
                }
            }
            item {
                RoundButton(
                    size = 43.dp,
                    backgroundColor = Black,
                    borderBackground = LightRed,
                    icon = R.drawable.ic_double_arrow_left,
                    iconColor = LightRed,
                    iconSize = 22.dp,
                    onClick = { viewModel.removePoint() })
            }
        }

    }

}

