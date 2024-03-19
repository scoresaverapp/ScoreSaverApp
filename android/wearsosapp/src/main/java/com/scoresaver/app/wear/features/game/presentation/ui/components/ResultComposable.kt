package com.scoresaver.app.wear.features.game.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scoresaver.app.R
import com.scoresaver.app.util.Blue
import com.scoresaver.app.util.Orange
import com.scoresaver.app.util.White
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.wear.components.typography.CustomText
import com.scoresaver.core_ui.components.icons.CustomImageVectorIcon

@Composable
fun ResultComposable(match: ResultData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomText(
                text = "12-04-20222",
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.weight(1f))

            CustomImageVectorIcon(
                modifier = Modifier.size(10.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile_selected),
                contentDescription = "",
                color = Orange
            )

            CustomText(
                text = "-",
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = White,
                    textAlign = TextAlign.Center
                )
            )

            CustomImageVectorIcon(
                modifier = Modifier.size(10.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile_selected),
                contentDescription = "",
                color = Orange
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .border(width = 1.dp, color = Orange, shape = RoundedCornerShape(8.dp))
                .padding(2.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomText(
                        text = "Team A",
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )
                    if (true) {
                        CustomImageVectorIcon(
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(10.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile_selected),
                            contentDescription = "",
                            color = Blue
                        )
                    }
                }
                CustomText(
                    text = "-",
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = White,
                        textAlign = TextAlign.Center
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomText(
                        text = "Team B",
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )
                    if (false) {
                        CustomImageVectorIcon(
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(10.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile_selected),
                            contentDescription = "",
                            color = Blue
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 30.dp)
            ) {
                ResultNumber(match)
            }
        }
    }
}

@Composable
fun ResultNumber(match: ResultData) {
    Column(
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Row {
            val team1Scores = match.listGameTeam1 ?: emptyList()
            val team2Scores = match.listGameTeam2 ?: emptyList()
            val team1List = if (team1Scores.size > 1) team1Scores.dropLast(1) else team1Scores
            team1List.zip(team2Scores) { team1Score, team2Score ->
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp) // Add padding to control spacing
                ) {
                    CustomText(
                        text = team1Score.toString(),
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )

                    CustomText(
                        text = "-",
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )

                    CustomText(
                        text = team2Score.toString(),
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}