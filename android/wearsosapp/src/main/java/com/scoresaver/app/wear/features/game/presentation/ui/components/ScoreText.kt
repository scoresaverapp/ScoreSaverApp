package com.scoresaver.app.wear.features.game.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scoresaver.app.R
import com.scoresaver.app.util.Grey
import com.scoresaver.app.util.White
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon
import com.scoresaver.app.wear.components.typography.CustomText


@Composable
fun ScoreText(
    currentPointPlayer1: String,
    currentPointPlayer2: String,
    numberGamePlayer1: String,
    numberGamePlayer2: String,
    numberSetPlayer1: Int,
    numberSetPlayer2: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(numberSetPlayer1 == 0 && numberSetPlayer2 == 0) {
            Spacer(modifier = Modifier.padding(end = 7.5.dp))
        }
        Column {
            for (i in 0 until numberSetPlayer1) {
                CustomImageVectorIcon(
                    modifier = Modifier.size(10.5.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_tennis_ball),
                    color = White,
                    contentDescription = ""
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomText(
                text = currentPointPlayer1,
                textStyle = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                )
            )
            CustomText(
                text = numberGamePlayer1,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Grey,
                )
            )
        }

        CustomText(
            modifier = Modifier
                .padding(vertical = 1.dp, horizontal = 2.dp)
                .alignByBaseline(),
            text = "-",
            textStyle = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight(400),
                color = White,
            )
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomText(
                text = currentPointPlayer2,
                textStyle = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                )
            )
            CustomText(
                text = numberGamePlayer2,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Grey,
                )
            )

        }
        if(numberSetPlayer1 == 0 && numberSetPlayer2 == 0) {
           Spacer(modifier = Modifier.padding(end = 7.5.dp))
        }
        Column {
            for (i in 0 until numberSetPlayer2) {
                CustomImageVectorIcon(
                    modifier = Modifier.size(10.5.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_tennis_ball),
                    color = White,
                    contentDescription = ""
                )
            }
        }
    }

}