package com.scoresaver.core_ui.components.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun CustomSpacer(horizontal: Boolean = false, size: Dp) {
    Spacer(modifier = Styles.rootContainer(horizontal = horizontal, size = size))
}

private object Styles {
    fun rootContainer(horizontal: Boolean, size: Dp): Modifier {
        return if (horizontal) Modifier.width(size) else Modifier.height(size)
    }
}