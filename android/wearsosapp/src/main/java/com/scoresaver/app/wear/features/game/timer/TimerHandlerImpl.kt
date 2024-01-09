package com.scoresaver.app.wear.features.game.timer

import com.scoresaver.app.wear.features.game.timer.Timer
import com.scoresaver.app.wear.features.game.timer.TimerHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class TimerHandlerImpl : TimerHandler {
    private var timer: Timer
    private var timerValue: MutableStateFlow<Pair<Int, Boolean>>
    private var scope: CoroutineScope? = null
    private var timerJob: Job? = null
    private var collectJob: Job? = null

    init {
        timer = Timer()
        timerValue = MutableStateFlow(Pair(timer.seconds, timer.getStatus()))
    }

    override fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    override fun addTimerListener(onTimerChangeCallback: (Int, Boolean) -> Unit) {
        collectJob = scope?.launch {
            timerValue.collect {
                onTimerChangeCallback(it.first, it.second)
            }
        }
    }

    override fun starTimer() {
        timer.startTimer()
        timerJob = scope?.launch {
            while (true) {
                delay(1000)
                timer.incrementSeconds()
                timerValue.emit(Pair(timer.seconds, timer.getStatus()))
            }
        }
    }

    private fun cancelTimerJob() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun cancelCollectJob() {
        collectJob?.cancel()
        collectJob = null
    }

    override fun stopTimer() {
        timer.stopTimer()
        cancelTimerJob()
        cancelCollectJob()
    }

    override fun clearTimer() {
        timer.stopTimer()
        cancelTimerJob()
        cancelCollectJob()
        timer = Timer()

        timerValue = MutableStateFlow(Pair(timer.seconds, timer.getStatus()))
    }

    override fun formatSeconds(value: Int) =
        timer.formatSeconds()


}