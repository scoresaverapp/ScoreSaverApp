package com.scoresaver.app.wear.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scoresaver.app.wear.db.dao.GameSettingsDao
import com.scoresaver.core.data.db.schema.GameSettingsEntity
import com.scoresaver.core.data.db.schema.UserEntity


const val DATABASE_NAME = "core-database"
@Database(
    entities = [
        GameSettingsEntity::class,
        UserEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class CoreDatabase: RoomDatabase() {
    abstract val gameSettingsDao: GameSettingsDao
}