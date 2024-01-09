package com.scoresaver.app.wear.features.game.health

import android.hardware.SensorManager
import com.scoresaver.core.data.db.schema.GENDER
import kotlinx.coroutines.CoroutineScope

interface Health {

    fun setScope(scope: CoroutineScope)
    fun startHeartRateListener(onHeartRateChangeCallback: (Float) -> Unit)
    fun stopHeartRateListener()

    fun getCalories(gender: GENDER, weight: Int, height: Int, heartRate: Float, minutes: Int): String

}