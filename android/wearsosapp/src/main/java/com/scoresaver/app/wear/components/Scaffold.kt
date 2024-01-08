package com.scoresaver.core_ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator

import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway

@Composable
fun MyScaffold(
    hideTimer: Boolean = false,
    scalingLazyState: ScalingLazyListState,
    content: @Composable () -> Unit,
) {
    Scaffold(
        timeText = {
            if (!hideTimer) {
                TimeText(
                    modifier = Modifier
                        .padding(
                            bottom = 8.dp
                        )
                        .scrollAway(rememberScalingLazyListState())
                )
            }
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = scalingLazyState
            )
        }
    ) {
        content()
    }
}