package com.scoresaver.app.wear.features.game.health

import com.scoresaver.app.util.db.entity.GENDER


class Calories {
    fun calculateCalories(
        age: Int,
        gender: GENDER,
        duration: Int, // in secondi
        weight: Double, // in chilogrammi
        met: Double, // MET (Metabolic Equivalent of Task)
        heartRatePerSecond: Int // battiti al secondo
    ): String {
        // Calcolo del BMR (Basal Metabolic Rate)
        val bmr = calculateBMR(age, gender, weight)
        // Calcolo del PAL (Physical Activity Level) in base ai battiti cardiaci
        val pal = calculatePAL(heartRatePerSecond)
        // Calcolo delle calorie bruciate per secondo
        val caloriesBurnedPerSecond = met * weight * 3.5 / 200 / 60 * pal // Calorie bruciate per secondo
        // Calcolo delle calorie totali bruciate durante l'attività, aggiungendo il BMR
        val total = (caloriesBurnedPerSecond * duration) + (bmr / 24 / 60 / 60 * duration)// Aggiungi il BMR alle calorie bruciate durante l'attività
        return "%.2f".format(total)
    }

    private fun calculateBMR(age: Int, gender: GENDER, weight: Double): Double {
        return if (gender == GENDER.MALE) {
            88.362 + (13.397 * weight) - (5.677 * age)
        } else {
            447.593 + (9.247 * weight) - (4.330 * age)
        }
    }

    private fun calculatePAL(heartRatePerSecond: Int): Double {
        // Puoi regolare il PAL in base al battito cardiaco o ad altre misure di attività
        return 1.2 + (heartRatePerSecond / 60 / 100) // Esempio di aggiustamento
    }

}