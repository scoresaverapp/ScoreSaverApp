package com.scoresaver.app.wear.features.game.timer

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerService : Service() {

    private var isRunning = false
    private var elapsedSeconds = 0
    private val timerBroadcastReceiver = TimerBroadcastReceiver()
    private var job: Job? = null


    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter("TIMER_TICK")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(timerBroadcastReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        }
    }

    private fun startTimer() {
        if (!isRunning) {
            isRunning = true
            job = CoroutineScope(Dispatchers.Main).launch {
                while (isRunning) {
                    elapsedSeconds++
                    val intent = Intent("TIMER_TICK")
                    intent.putExtra("seconds", elapsedSeconds)
                    sendBroadcast(intent)
                    delay(1000)
                }
            }
        }
    }

    private fun stopTimer() {
        isRunning = false
        job?.cancel()
        job = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                "START_TIMER" -> startTimer()
                "STOP_TIMER" -> stopTimer()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}