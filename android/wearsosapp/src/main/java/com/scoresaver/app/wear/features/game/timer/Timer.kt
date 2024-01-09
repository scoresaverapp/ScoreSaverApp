package com.scoresaver.app.wear.features.game.timer

class Timer {
    var seconds: Int = 0

    private var isRunning = false

    fun incrementSeconds() {
        seconds++
    }

    fun formatSeconds() =
        "%02d:%02d:%02d".format(seconds / 3600, (seconds % 3600) / 60, seconds % 60)

    fun startTimer() {
        isRunning = true
    }

    fun stopTimer() {
        isRunning = false
    }

    fun getStatus() = isRunning
}