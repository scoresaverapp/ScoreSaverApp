package com.scoresaver.app.wear.features.new_game.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListAnchorType
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.scoresaver.app.R
import com.scoresaver.app.util.Orange
import com.scoresaver.app.wear.features.new_game.presentation.ViewModelFragment
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.FullWidthRoundSwitchButton
import com.scoresaver.core_ui.components.typography.CustomText

@Composable
internal fun GameTypeScreen(
    viewModel: ViewModelFragment.GameType
) {
    val single = viewModel.getSingleGameSwitchValue()
    val double = viewModel.getDoubleGameSwitchValue()

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
                    text = stringResource(id = R.string.type_game),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange,
                    )
                )
            }

            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.single_game),
                    checked = single.switchValue,
                    onClick = viewModel::setSingleSwitchValue
                )
            }

            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.double_game),
                    checked = double.switchValue,
                    onClick = viewModel::setDoubleSwitchValue
                )
            }
        }
    }
}
