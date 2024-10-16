package com.scoresaver.app.wear.features.game.health

import android.hardware.Sensor
import android.hardware.SensorManager
import com.scoresaver.app.util.db.entity.GENDER
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

internal class HealthImpl @Inject constructor(
    private val sensorManager: SensorManager
) : Health {
    private lateinit var hearthRate: HearthRate
    private var calories: Calories = Calories()
    private var scope: CoroutineScope? = null


    override fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    override fun startHeartRateListener(onHeartRateChangeCallback: (Float) -> Unit) {
        hearthRate = HearthRate {
            onHeartRateChangeCallback(it)
        }

        val heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)

        sensorManager.registerListener(
            hearthRate,
            heartRateSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun stopHeartRateListener() {
        sensorManager.unregisterListener(hearthRate)
    }

    override fun getCalories(
        age: Int, gender: GENDER, weight: Int, height: Int, heartRate: Float, minutes: Int
    ): String = calories.calculateCalories(
        age = age,
        gender = gender, weight = weight.toDouble(), heartRatePerSecond = heartRate.toInt(), met = 7.0, duration = minutes,
    )
}