package com.scoresaver.app.util.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_settings")
data class GameSettingsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val gameType: GAME_TYPE,
    val gamePoint: GAME_POINT,
    val sportType: SPORT_TYPE
)

enum class GAME_TYPE(val value: String){
    SINGLE("single"),
    DOUBLE("double")
}

enum class GAME_POINT(val value: String) {
    ADV("advantages"),
    KILLER("killer")
}

enum class SPORT_TYPE(val value: String) {
    PADEL("padel"),
    TENNIS("tennis"),
}