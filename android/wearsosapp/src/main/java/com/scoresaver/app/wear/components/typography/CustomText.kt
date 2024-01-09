package com.scoresaver.app.wear.components.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.wear.compose.material.Text

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle
    )
}