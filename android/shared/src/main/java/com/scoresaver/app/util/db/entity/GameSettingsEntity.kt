package com.scoresaver.core.data.db.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_settings")
data class GameSettingsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val gameType: GAME_TYPE,
    val gamePoint: GAME_POINT,
)

enum class GAME_TYPE(val value: String){
    SINGLE("single"),
    DOUBLE("double")
}

enum class GAME_POINT(val value: String) {
    ADV("advantages"),
    KILLER("killer")
}