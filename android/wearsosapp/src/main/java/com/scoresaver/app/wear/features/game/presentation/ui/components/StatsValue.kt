package com.scoresaver.app.wear.features.game.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.scoresaver.app.util.White
import com.scoresaver.core_ui.components.typography.CustomText

@Composable
fun StatsValue(text: String, textColor: Color = White, valueColor: Color = White, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CustomText(
            text = text,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = textColor,
            )
        )
        CustomText(
            text = value,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = valueColor,
            )
        )
    }
}