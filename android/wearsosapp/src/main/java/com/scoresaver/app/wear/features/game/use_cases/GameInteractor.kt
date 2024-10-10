package com.scoresaver.app.wear.features.game.use_cases

import com.scoresaver.app.util.db.entity.GENDER
import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity
import com.scoresaver.app.wear.features.game.model.Team
import kotlinx.coroutines.flow.Flow

interface GameInteractor {
    fun startHeartRateListener(onHeartRateChangeCallback: (Float) -> Unit)
    fun stopHeartRateListener()
    fun getCalories(
        age: Int,
        gender: GENDER,
        weight: Int,
        height: Int,
        heartRate: Float,
        seconds: Int
    ): String

    fun addPoint(team: Team, isKillerPointActive: Boolean)
    fun removeLastPoint()
    fun getPointScoreTeam1(): String
    fun getPointScoreTeam2(): String
    fun checkWinGame(): Boolean
    fun getGameScoreTeam1(): String
    fun getGameScoreTeam2(): String
    fun getSetScoreTeam1(): Int

    fun getSetScoreTeam2(): Int

    fun saveResult()
    suspend fun loadHistoryMatches(): Flow<List<ResultData>>
    suspend fun getUserData(): UserEntity?
    suspend fun getGameSettings(): GameSettingsEntity?

    suspend fun deleteSettingsData()
}