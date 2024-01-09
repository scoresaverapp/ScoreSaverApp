package com.scoresaver.app.wear.features.new_game.model

internal data class SwitchState<T> (
    val type: T,
    val switchValue: Boolean
)