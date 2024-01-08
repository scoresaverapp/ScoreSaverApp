package com.scoresaver.app.wear.model

internal sealed class Team {
    data object One: Team()
    data object Two: Team()
}
