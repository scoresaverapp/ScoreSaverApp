package com.scoresaver.app.wear.features.game.timer

import kotlinx.coroutines.CoroutineScope

interface TimerHandler {
    fun setScope(scope: CoroutineScope)
    fun addTimerListener(onTimerChangeCallback: (Int, Boolean) -> Unit)
    fun starTimer()
    fun stopTimer()
    fun clearTimer()
    fun formatSeconds(value: Int) : String
}