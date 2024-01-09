package com.scoresaver.app.wear.features.game.health

import com.scoresaver.app.util.db.entity.GENDER
import kotlinx.coroutines.CoroutineScope

interface Health {

    fun setScope(scope: CoroutineScope)
    fun startHeartRateListener(onHeartRateChangeCallback: (Float) -> Unit)
    fun stopHeartRateListener()

    fun getCalories(age: Int, gender: GENDER, weight: Int, height: Int, heartRate: Float, minutes: Int): String

}