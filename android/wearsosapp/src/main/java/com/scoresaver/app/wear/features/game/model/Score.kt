package com.scoresaver.app.wear.features.game.model


sealed class PointScore(val score: String) {
    data class Zero(val s: String = "0"): PointScore(s)
    data class Fifteen(val s: String = "15"): PointScore(s)
    data class Thirty(val s: String = "30"): PointScore(s)
    data class Forty(val s: String = "40"): PointScore(s)
    data class Sixty(val s: String = "60"): PointScore(s)
    data class Adv(val s: String = "A"): PointScore(s)
    data class Tiebreak(val s: String): PointScore(s)
    data class TiebreakAdv(val s: String = "1"): PointScore(s)

}


enum class GameScore(val score: String) {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
}

fun mapIntToPointScore(points: Int): PointScore {
    return when (points) {
        0 -> PointScore.Zero()
        15 -> PointScore.Fifteen()
        30 -> PointScore.Thirty()
        40 -> PointScore.Forty()
        60 -> PointScore.Sixty()
        else -> {
            PointScore.Tiebreak(points.toString())
        }
    }
}

fun mapIntToGameScore(points: Int): GameScore {
    return when (points) {
        0 -> GameScore.ZERO
        1 -> GameScore.ONE
        2 -> GameScore.TWO
        3 -> GameScore.THREE
        4 -> GameScore.FOUR
        5 -> GameScore.FIVE
        6 -> GameScore.SIX
        else -> {
            GameScore.SEVEN
        }
    }
}