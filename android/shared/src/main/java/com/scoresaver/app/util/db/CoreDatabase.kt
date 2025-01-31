package com.scoresaver.app.wear.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scoresaver.app.util.db.dao.GameSettingsDao
import com.scoresaver.app.util.db.entity.GameSettingsEntity
import com.scoresaver.app.util.db.entity.ResultData
import com.scoresaver.app.util.db.entity.UserEntity


const val DATABASE_NAME = "core-database"
@Database(
    entities = [
        GameSettingsEntity::class,
        UserEntity::class,
        ResultData::class
    ],
    version = 4,
    exportSchema = false
)
abstract class CoreDatabase: RoomDatabase() {
    abstract val gameSettingsDao: GameSettingsDao
}