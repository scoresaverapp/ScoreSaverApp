package com.scoresaver.app.wear.features.game.repository

import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.core.data.db.schema.UserEntity

interface GameRepository {
    suspend fun getUserData(): UserEntity?
    suspend fun getGameSettings(): GameSettingsEntity?
}