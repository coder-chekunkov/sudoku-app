package com.cdr.app.model.statistic

import android.database.sqlite.SQLiteConstraintException
import com.cdr.app.model.database.StatisticGameInfoTuple
import com.cdr.app.model.database.dao.StatisticDao
import com.cdr.app.model.database.entities.StatisticDbEntity
import com.cdr.app.utils.DbUnknownException
import com.cdr.app.utils.EmptyStatisticListException
import com.cdr.app.utils.StatisticGameInfoNotExist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatisticDatabaseRepository(
    private val statisticDao: StatisticDao
) : StatisticRepository {


    override suspend fun insertNewGameStatisticInfo(newStatistic: Statistic) {
        withContext(Dispatchers.IO) {
            try {
                val newStatisticEntity = StatisticDbEntity.toStatisticDbEntity(newStatistic)
                statisticDao.insertNewStatisticGameInfo(newStatisticEntity)
            } catch (e: SQLiteConstraintException) {
                throw DbUnknownException()
            }
        }
    }

    override suspend fun getAllGamesStatisticInfo(): List<StatisticGameInfoTuple> {
        return withContext(Dispatchers.IO) {
            val allStatistic = statisticDao.getAllStatisticGamesInfo()
            if (allStatistic.isEmpty()) throw EmptyStatisticListException()

            return@withContext allStatistic
        }
    }

    override suspend fun removeAllGamesStatisticInfo() {
        withContext(Dispatchers.IO) {
            try {
                statisticDao.removeAllStatisticGamesInfo()
            } catch (e: SQLiteConstraintException) {
                throw DbUnknownException()
            }
        }
    }

    override suspend fun removeGameStatisticInfoById(id: Long) {
        withContext(Dispatchers.IO) {
            try {
                statisticDao.removeStatisticGameInfoById(id)
            } catch (e: SQLiteConstraintException) {
                throw StatisticGameInfoNotExist()
            }
        }
    }
}