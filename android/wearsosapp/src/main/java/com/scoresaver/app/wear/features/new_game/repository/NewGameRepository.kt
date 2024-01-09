package com.scoresaver.app.wear.features.new_game.repository

import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.app.util.db.entity.UserEntity

interface NewGameRepository {

    suspend fun insertDataUser(userEntity: UserEntity)
    suspend fun insertGameSettings(gameSettingsEntity: GameSettingsEntity)

    suspend fun getUser(): UserEntity
    suspend fun getGameSettings(): GameSettingsEntity

}