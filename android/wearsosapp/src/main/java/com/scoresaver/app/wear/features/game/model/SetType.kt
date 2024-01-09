package com.scoresaver.app.wear.features.game.model

sealed class SetType(val team: Team, val value: Int) {

    data class Base(val t: Team, val v: Int = 1): SetType(t, v)
}