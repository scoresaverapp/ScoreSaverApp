package com.scoresaver.newgame_repository.repository

import com.scoresaver.app.wear.db.dao.GameSettingsDao
import com.scoresaver.app.wear.features.new_game.repository.NewGameRepository
import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.core.data.db.schema.UserEntity
import javax.inject.Inject

internal class NewNewGameRepositoryImpl @Inject constructor(
    private val gameSettingsDao: GameSettingsDao,
) : NewGameRepository {
    override suspend fun insertDataUser(userEntity: UserEntity) {
        gameSettingsDao.insertDataUser(userEntity)
    }

    override suspend fun insertGameSettings(gameSettingsEntity: GameSettingsEntity) {
        gameSettingsDao.insertGameSettings(gameSettingsEntity)
    }

    override suspend fun getUser(): UserEntity {
        return gameSettingsDao.getUserData()
    }

    override suspend fun getGameSettings(): GameSettingsEntity {
        return gameSettingsDao.getGameSettings()
    }


}