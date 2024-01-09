package com.scoresaver.app.wear.features.new_game.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scoresaver.app.util.White
import com.scoresaver.app.wear.components.typography.CustomText

@Composable
internal fun NumberBox(number: String) {
    Box(
        modifier = Modifier
            .height(18.dp)
            .width(18.dp)
            .border(1.dp, White, shape = RoundedCornerShape(1.74.dp))
    ) {
        CustomText(
            modifier = Modifier.align(Alignment.Center),
            text = number,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                color = White,
                textAlign = TextAlign.Center
            )
        )
    }
}