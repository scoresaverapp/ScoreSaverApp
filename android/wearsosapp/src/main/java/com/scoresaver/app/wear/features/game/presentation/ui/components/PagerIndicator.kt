package com.scoresaver.app.wear.features.game.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.scoresaver.app.util.White
import com.scoresaver.core_ui.components.layout.CustomSpacer

@Composable
private fun RenderItem(isSelected: Boolean, isLast: Boolean) {
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(10.dp)
            .clip(RoundedCornerShape(26.19.dp))
            .background(White)
    )

    if (isLast) {
        CustomSpacer(size = 10.dp, horizontal = true)
    }
}

@Composable
internal fun PagerIndicator(pagesCount: Int, currentPage: Int) {
    Row {
        List(pagesCount) { index ->
            val isSelected = index == currentPage
            val isLast = index < pagesCount - 1
            RenderItem(isSelected = isSelected, isLast = isLast)
        }
    }
}