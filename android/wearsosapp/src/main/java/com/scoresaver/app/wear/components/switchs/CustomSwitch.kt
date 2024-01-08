package com.scoresaver.core_ui.components.switchs

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Switch
import androidx.wear.compose.material.SwitchColors

@Composable
fun CustomSwitch(
    onCheckedChange: ((Boolean) -> Unit)? = null,
    checked: Boolean,
    colors: SwitchColors,
    enabled: Boolean = true,
) {
        Switch(
            onCheckedChange = onCheckedChange,
            checked = checked,
            enabled = enabled,
            colors = colors
        )
}