package com.scoresaver.newgame_interactor.interactors

import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.core.data.db.schema.UserEntity

interface NewGameInteractor {
    suspend fun insertDataUser(userEntity: UserEntity)
    suspend fun insertGameSettings(gameSettingsEntity: GameSettingsEntity)

    suspend fun getUser(): UserEntity
    suspend fun getGameSettings(): GameSettingsEntity
}