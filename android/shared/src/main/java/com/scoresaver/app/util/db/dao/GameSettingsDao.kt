package com.scoresaver.app.util.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.app.util.db.entity.UserEntity

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
}
