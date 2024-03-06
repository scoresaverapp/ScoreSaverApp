package com.scoresaver.app.util.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameSettings(gameSettings: GameSettingsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUser(userEntity: UserEntity)

    @Query("SELECT * FROM game_settings")
    fun getGameSettings(): GameSettingsEntity
    @Query("SELECT * FROM user")
    fun getUserData(): UserEntity

    @Insert
    fun insertResultData(resulData: ResultData)

    @Query("SELECT * FROM result_match")
    fun getHistoryMatches(): Flow<List<ResultData>>
}
