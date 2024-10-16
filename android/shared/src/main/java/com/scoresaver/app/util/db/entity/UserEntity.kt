package com.scoresaver.app.util.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_TABLE = "user"
@Entity(tableName = USER_TABLE)
data class UserEntity (
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    val height: Int,
    val weight: Int,
    val age: Int,
    val gender: GENDER
)

enum class GENDER(var value: String) {
    MALE("male"),
    FEMALE("female")
}