package com.scoresaver.app.wear.model

internal sealed class GameSetting {
    data object Single: GameSetting()
    data object Double: GameSetting()
    data object Advantages: GameSetting()
    data object KillerPoint: GameSetting()

    data object Male: GameSetting()
    data object Female: GameSetting()
}
