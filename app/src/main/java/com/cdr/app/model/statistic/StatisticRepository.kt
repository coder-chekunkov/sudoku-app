package com.cdr.app.model.statistic

import com.cdr.app.model.database.StatisticGameInfoTuple
import com.cdr.core.model.Repository

interface StatisticRepository : Repository {

    suspend fun insertNewGameStatisticInfo(newStatistic: Statistic)

    suspend fun getAllGamesStatisticInfo(): List<StatisticGameInfoTuple>

    suspend fun removeAllGamesStatisticInfo()

    suspend fun removeGameStatisticInfoById(id: Long)

}