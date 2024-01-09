package com.scoresaver.app.wear.features.game.model

sealed class PointType(val team: Team, val value: Int) {
    data class Point10(val t: Team, val v: Int = 10): PointType(t,v)
    data class Point15(val t: Team, val v: Int = 15): PointType(t, v)
    data class Advantages (val t: Team, val  v: Int = 0): PointType(t, v)
    data class Deuce(val t: Team, val v: Int = 0): PointType(t, v)
    data class Killer(val t: Team, val v: Int = 0): PointType(t, v)
    data class TieBreakPoint(val t: Team, val v: Int = 1): PointType(t,v)
    data class TieBreakAdvantages(val t: Team, val v: Int = 1): PointType(t,v)
}







