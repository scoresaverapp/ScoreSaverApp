package com.scoresaver.app.wear.features.new_game.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.features.new_game.presentation.ViewModelFragment
import com.scoresaver.app.wear.components.MyScaffold
import com.scoresaver.core_ui.components.buttons.FullWidthRoundSwitchButton
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import com.scoresaver.core_ui.components.typography.CustomText

@Composable
internal fun GameRuleScreen(
    viewModel: ViewModelFragment.GameRule
) {
    val advantages = viewModel.getAdvantagesSwitchValue()
    val killerPoint = viewModel.getKillerPointSwitchValue()

    val scalingLazyState = remember {
        ScalingLazyListState(
            initialCenterItemIndex = 1,
            initialCenterItemScrollOffset = 40
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
                    text = stringResource(id = R.string.rules_game),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Orange,
                    )
                )
            }
            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.advantages),
                    checked = advantages.switchValue,
                    onClick = viewModel::setAdvantagesSwitchValue
                )
            }

            item {
                FullWidthRoundSwitchButton(
                    text = stringResource(R.string.killer_text),
                    checked = killerPoint.switchValue,
                    onClick = viewModel::setKillerPointSwitchValue
                )
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomImageVectorIcon(
                        modifier = Modifier
                            .alignByBaseline()
                            .padding(top = 3.dp, start = 4.dp, end = 4.dp)
                            .size(11.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning),
                        contentDescription = "title_view",
                        color = Orange
                    )
                    CustomText(
                        text = stringResource(id = R.string.description_killer_point),
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                        )
                    )
                }

            }
        }
    }
}