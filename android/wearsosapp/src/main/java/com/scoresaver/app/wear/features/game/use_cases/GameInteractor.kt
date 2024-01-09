package com.scoresaver.app.wear.features.game.use_cases

import com.scoresaver.app.util.db.entity.GENDER
import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.app.util.db.entity.UserEntity
import com.scoresaver.app.wear.features.game.model.Team
import kotlinx.coroutines.CoroutineScope

interface GameInteractor {
    fun startTimer(
        scope: CoroutineScope,
        onTimerChangeCallback: (Int, Boolean) -> Unit
    )
    fun stopTimer()
    fun clearTimer()
    fun startHeartRateListener(onHeartRateChangeCallback: (Float) -> Unit)
    fun stopHeartRateListener()
    fun getCalories(age: Int, gender: GENDER, weight: Int, height: Int, heartRate: Float, seconds: Int): String
    fun formatSeconds(value: Int): String
    fun addPoint(team: Team, isKillerPointActive: Boolean)
    fun removeLastPoint()
    fun getPointScoreTeam1(): String
    fun getPointScoreTeam2(): String
    fun checkWinGame(): Boolean
    fun getGameScoreTeam1(): String
    fun getGameScoreTeam2(): String
    fun getSetScoreTeam1(): Int

    fun getSetScoreTeam2(): Int
    suspend fun getUserData(): UserEntity?
    suspend fun getGameSettings(): GameSettingsEntity?
}