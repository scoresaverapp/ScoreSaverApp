package com.scoresaver.newgame_interactor.interactors

import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.app.util.db.entity.UserEntity
import com.scoresaver.app.wear.features.new_game.repository.NewGameRepository
import javax.inject.Inject

internal class NewGameInteractorImpl @Inject constructor(
    private val newGameRepository: NewGameRepository
) : NewGameInteractor {
    override suspend fun insertDataUser(userEntity: UserEntity) {
        newGameRepository.insertDataUser(userEntity)
    }

    override suspend fun insertGameSettings(gameSettingsEntity: GameSettingsEntity) {
        newGameRepository.insertGameSettings(gameSettingsEntity)
    }

    override suspend fun getUser(): UserEntity {
        return newGameRepository.getUser()
    }

    override suspend fun getGameSettings(): GameSettingsEntity {
        return newGameRepository.getGameSettings()
    }


}