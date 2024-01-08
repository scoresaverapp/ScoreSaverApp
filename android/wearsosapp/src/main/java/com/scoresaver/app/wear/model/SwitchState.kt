package com.scoresaver.app.wear.model

internal data class SwitchState<T> (
    val type: T,
    val switchValue: Boolean
)