package com.scoresaver.app.wear.features.game.timer

import android.content.Context
import android.os.PowerManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class TimerHandlerImpl @Inject constructor(
    private val context: Context
) : TimerHandler {
    private var timer: Timer
    private var timerValue: MutableStateFlow<Pair<Int, Boolean>>
    private var scope: CoroutineScope? = null
    private var timerJob: Job? = null
    private var collectJob: Job? = null
    private var wakeLock: PowerManager.WakeLock? = null

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
        acquireWakeLock()
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
        releaseWakeLock()
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

    private fun acquireWakeLock() {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp:TimerWakeLock")
        wakeLock?.acquire(10*60*1000L /*10 minutes*/)
    }

    private fun releaseWakeLock() {
        wakeLock?.release()
        wakeLock = null
    }


}