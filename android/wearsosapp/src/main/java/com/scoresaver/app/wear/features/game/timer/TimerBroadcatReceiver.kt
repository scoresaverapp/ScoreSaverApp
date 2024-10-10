package com.scoresaver.app.wear.features.game.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimerBroadcastReceiver : BroadcastReceiver() {
    var onTimerTick: ((Int) -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {
        val seconds = intent.getIntExtra("seconds", 0)
        onTimerTick?.invoke(seconds)
    }
}