package com.cdr.app.model.database.entities

import androidx.room.*
import com.cdr.app.model.statistic.Statistic

@Entity(
    tableName = "statistic",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = ResultsDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["result_id"]
        ),
        ForeignKey(
            entity = DifficultyLevelsDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["difficult_id"]
        )
    ]
)
data class StatisticDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "result_id") val resultId: Long,
    @ColumnInfo(name = "difficult_id") val difficultId: Long,
    val mistakes: Long,
    val points: Long,
    @ColumnInfo(name = "elapsed_time") val elapsedTime: String
) {

    companion object {
        fun toStatisticDbEntity(statistic: Statistic): StatisticDbEntity = StatisticDbEntity(
            id = 0,
            resultId = statistic.resultId,
            difficultId = statistic.difficultId,
            mistakes = statistic.mistakes,
            points = statistic.points,
            elapsedTime = statistic.elapsedTime
        )
    }
}