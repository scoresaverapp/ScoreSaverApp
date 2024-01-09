package com.scoresaver.app.wear.features.new_game.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scoresaver.app.R
import com.scoresaver.app.util.Black
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.features.new_game.model.Player
import com.scoresaver.core_ui.components.buttons.RoundButton
import com.scoresaver.core_ui.components.layout.CustomSpacer
import com.scoresaver.core_ui.components.typography.CustomText

@Composable
internal fun ServiceOrderTeamTwo(
    playerOne: Player,
    playerTwo: Player,
    swapTeamOrder: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            modifier = Modifier.padding(end = 8.dp),
            text = stringResource(R.string.team_2), textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                color = White,
            )
        )

        CustomSpacer(size = 7.5.dp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                PlayerOrder(
                    isUserPlayer = playerOne.isUserPlayer,
                    number = playerOne.serviceOrder.number
                )
                CustomSpacer(size = 10.5.dp)
                PlayerOrder(
                    isUserPlayer = playerTwo.isUserPlayer,
                    number = playerTwo.serviceOrder.number
                )
            }
            CustomSpacer(size = 10.dp, horizontal = true)
            RoundButton(
                size = 25.dp,
                backgroundColor = Orange,
                icon = R.drawable.ic_round_arrow_right,
                iconColor = Black,
                iconSize = 15.dp,
                onClick = { swapTeamOrder() })
        }
    }
}