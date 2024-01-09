package com.scoresaver.app.wear.features.game.health

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class HearthRate(
    private val onHeartRateChangeCallback: (Float) -> Unit
) : SensorEventListener {
    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let {
            onHeartRateChangeCallback(it.values[0])
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

}