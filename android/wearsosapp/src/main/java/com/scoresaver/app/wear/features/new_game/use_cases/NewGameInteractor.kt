package com.scoresaver.newgame_interactor.interactors

import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.UserEntity

interface NewGameInteractor {
    suspend fun insertDataUser(userEntity: UserEntity)
    suspend fun insertGameSettings(gameSettingsEntity: GameSettingsEntity)

    suspend fun getUser(): UserEntity
    suspend fun getGameSettings(): GameSettingsEntity
}