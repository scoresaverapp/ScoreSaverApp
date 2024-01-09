package com.scoresaver.app.wear.features.new_game.model

internal sealed class Team {
    data object One: Team()
    data object Two: Team()
}
