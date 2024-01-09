package com.scoresaver.app.wear.features.game.repository

import com.scoresaver.app.wear.db.dao.GameSettingsDao
import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.core.data.db.schema.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val gameSettingsDao: GameSettingsDao
) : GameRepository {
    override suspend fun getUserData(): UserEntity {
        return withContext(Dispatchers.IO) {
            gameSettingsDao.getUserData()
        }
    }

    override suspend fun getGameSettings(): GameSettingsEntity {
        return withContext(Dispatchers.IO) {
            gameSettingsDao.getGameSettings()
        }
    }
}