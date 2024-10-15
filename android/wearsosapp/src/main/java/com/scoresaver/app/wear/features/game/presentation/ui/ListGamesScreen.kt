package com.scoresaver.app.wear.features.game.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import com.scoresaver.app.R
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.app.wear.components.typography.CustomText
import com.scoresaver.app.wear.features.game.presentation.GameViewModel
import com.scoresaver.app.wear.features.game.presentation.ui.components.ResultComposable

@Composable
internal fun ListGameScreen(
    viewModel: GameViewModel
) {
    val listMatches by viewModel.historyMatches.collectAsState(initial = emptyList())
    viewModel.loadHistoryMatches()
    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = if (listMatches.isEmpty()) 0 else 1,
            initialCenterItemScrollOffset = if (listMatches.isEmpty()) 0 else 0
        )
    }

    MyScaffold(scalingLazyState = scalingLazyState) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 1, itemOffset = 0),
            state = scalingLazyState,
            anchorType = if (listMatches.isEmpty()) {
                ScalingLazyListAnchorType.ItemStart
            } else {
                ScalingLazyListAnchorType.ItemCenter
            }
        ) {
            item {
                CustomText(
                    text = stringResource(id = R.string.history_game),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange,
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }

            if (listMatches.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CustomText(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = stringResource(id = R.string.empty_history),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                color = White,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            } else {
                items(listMatches.asReversed().take(5)) { match ->
                    ResultComposable(match)
                }
            }

            item {
                Column {
                    Modifier.weight(1f)
                }
            }
        }
    }
}

