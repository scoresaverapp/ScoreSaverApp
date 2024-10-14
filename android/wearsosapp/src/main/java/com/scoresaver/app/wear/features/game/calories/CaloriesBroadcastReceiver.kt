package com.scoresaver.app.wear.features.game.calories

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CaloriesBroadcastReceiver : BroadcastReceiver() {
    var onCaloriesTick: ((Float) -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {
        val calories = intent.getFloatExtra("calories", 0f)
        onCaloriesTick?.invoke(calories)
    }
}