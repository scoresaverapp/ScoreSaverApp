package com.scoresaver.app.wear.features.game.repository

import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getUserData(): UserEntity?
    suspend fun getGameSettings(): GameSettingsEntity?
    suspend fun insetResultMatch(resultData: ResultData)
    suspend fun getHistoryMatches(): Flow<List<ResultData>>
    suspend fun deleteSettingsData()
}