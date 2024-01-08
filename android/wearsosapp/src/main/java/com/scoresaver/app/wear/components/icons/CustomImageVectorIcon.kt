package com.scoresaver.core_ui.components.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.wear.compose.material.Icon

@Composable
fun CustomImageVectorIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    color: Color,
    contentDescription: String
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        tint = color,
        contentDescription = contentDescription
    )
}