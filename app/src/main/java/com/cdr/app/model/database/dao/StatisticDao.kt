package com.cdr.app.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cdr.app.model.database.StatisticGameInfoTuple
import com.cdr.app.model.database.entities.StatisticDbEntity

@Dao
interface StatisticDao {

    @Insert(entity = StatisticDbEntity::class)
    fun insertNewStatisticGameInfo(newStatistic: StatisticDbEntity)

    @Query("SELECT statistic.id, result_name, difficulty_name, mistakes, points, elapsed_time FROM statistic\n" +
            "INNER JOIN results ON statistic.result_id = results.id\n" +
            "INNER JOIN difficulty_levels ON statistic.difficult_id = difficulty_levels.id;")
    fun getAllStatisticGamesInfo(): List<StatisticGameInfoTuple>

    @Query("DELETE FROM statistic;")
    fun removeAllStatisticGamesInfo()

    @Query("DELETE FROM statistic WHERE id = :statisticId")
    fun removeStatisticGameInfoById(statisticId: Long)

}