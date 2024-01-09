package com.scoresaver.app.wear.features.game.health

import com.scoresaver.app.util.db.entity.GENDER

/**
 * ((−55.0969+(0.6309×Heart Rate)+(0.1988×Weight in kg)+(0.2017×Age))/4.184)×Time in minutes
 */

/**
 * ((−20.4022+(0.4472×Heart Rate)−(0.1263×Weight in kg)+(0.074×Age))/4.184)
 */

/**
 * 88.362+(13.397×weight in kg)+(4.799×height in cm)−(5.677×age in years)
 */

class Calories {

    fun calculateCalories(
        age: Int,
        gender: GENDER,
        weight: Int,
        height: Int,
        heartRate: Float,
        minutes: Int
    ): String {
        return if (gender == GENDER.MALE) {
            (((28 * 0.2017) - (weight * 0.09036) + (heartRate * 0.6309) - 55.0969) * 4.184).toString()
        } else {
            (((28 * 0.2017) - (weight * 0.09036) + (heartRate * 0.6309) - 55.0969) * 4.184).toString()
        }

    }
}