package com.scoresaver.app.wear.features.game.timer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.scoresaver.app.R
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
    private var startTime: Long = 0
    private val timerBroadcastReceiver = TimerBroadcastReceiver()
    private var serviceJob = Job()
    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter("TIMER_TICK")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(timerBroadcastReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        }
        createNotificationChannel()
    }

    private fun startTimer() {
        if (!isRunning) {
            isRunning = true
            startTime = SystemClock.elapsedRealtime() - (elapsedSeconds * 1000)
            serviceJob = Job()
            coroutineScope = CoroutineScope(Dispatchers.Main + serviceJob)

            coroutineScope.launch {

                while (isRunning) {
                    val currentTime = SystemClock.elapsedRealtime()
                    elapsedSeconds = ((currentTime - startTime) / 1000).toInt()
                    val intent = Intent("TIMER_TICK")
                    intent.putExtra("seconds", elapsedSeconds)
                    sendBroadcast(intent)
                    delay(1000)
                }
            }
        } else {

        }
    }

    private fun stopTimer() {
        if (isRunning) {
            isRunning = false
            serviceJob.cancel()
        }
    }

    private fun resetTimer() {
        if (isRunning) {
            stopTimer()
        }
        elapsedSeconds = 0
        startTime = 0
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, "CHANNEL")
            .setContentTitle("Timer Service")
            .setContentText("Timer is running...")
            .setSmallIcon(R.drawable.ic_clock)
            .setOngoing(true)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .build()

        startForeground(1, notification)

        intent?.let {
            when (it.action) {
                "START_TIMER" -> startTimer()
                "STOP_TIMER" -> stopTimer()
                "RESET_TIMER" -> resetTimer()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        unregisterReceiver(timerBroadcastReceiver)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "CHANNEL",
            "Score Saver Timer",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}