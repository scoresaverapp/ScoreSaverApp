package com.scoresaver.app.util.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

@Entity(tableName = "result_match")
@TypeConverters(ListConverter::class)
data class ResultData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val listGameTeam1: List<Int>? = null,
    val listGameTeam2: List<Int>? = null,
    val winner: Int
)


class ListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<Int> {
        return gson.fromJson(value, Array<Int>::class.java).toList()
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return gson.toJson(list)
    }
}