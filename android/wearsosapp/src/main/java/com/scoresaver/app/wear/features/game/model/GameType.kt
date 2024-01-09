package com.scoresaver.app.wear.features.game.model

sealed class GameType(val team: Team, val value: Int) {
    data class Base(val t: Team, val v: Int = 1): GameType(t, v)
    data class Advantages(val t: Team, val v: Int = 1): GameType(t, v)
    data class TieBreak(val t: Team, val v: Int = 1): GameType(t, v)
}