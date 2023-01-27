package com.cdr.app.model.database

import androidx.room.ColumnInfo

data class StatisticGameInfoTuple(
    val id: Long,
    @ColumnInfo(name = "result_name") val resultName: String,
    @ColumnInfo(name = "difficulty_name") val difficultyName: String,
    val mistakes: Long,
    val points: Long,
    @ColumnInfo(name = "elapsed_time") val elapsedTime: String
) {
    override fun toString(): String =
        "GameId: $id[mistakes: $mistakes; points: $points; time: $elapsedTime]"
}