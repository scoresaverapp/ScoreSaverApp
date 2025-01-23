package com.scoresaver.app.wear.components.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.Text

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        maxLines = maxLines,
        overflow = overflow,
        softWrap = softWrap,
        textAlign = textAlign
    )
}