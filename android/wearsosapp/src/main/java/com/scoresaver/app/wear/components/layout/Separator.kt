package com.scoresaver.core_ui.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Separator(
    paddingLeft: Dp = 0.dp,
    paddingRight: Dp = 0.dp,
    backgroundColor: Color,
    height: Dp = 1.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingLeft, end = paddingRight)
            .background(backgroundColor)
            .height(height)
    ) {

    }
}