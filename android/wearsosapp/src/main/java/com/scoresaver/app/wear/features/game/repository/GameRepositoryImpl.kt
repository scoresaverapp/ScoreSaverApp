package com.scoresaver.app.wear.features.game.repository

import com.scoresaver.app.util.db.dao.GameSettingsDao
import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    override suspend fun insetResultMatch(resultData: ResultData) {
        gameSettingsDao.insertResultData(resultData)
    }

    override suspend fun getHistoryMatches(): Flow<List<ResultData>> {
        return withContext(Dispatchers.IO) {
            gameSettingsDao.getHistoryMatches()
        }
    }
}