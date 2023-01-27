package com.cdr.app.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cdr.app.model.database.dao.StatisticDao
import com.cdr.app.model.database.entities.DifficultyLevelsDbEntity
import com.cdr.app.model.database.entities.ResultsDbEntity
import com.cdr.app.model.database.entities.StatisticDbEntity

@Database(
    version = 1,
    entities = [
        DifficultyLevelsDbEntity::class,
        ResultsDbEntity::class,
        StatisticDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStatisticDao(): StatisticDao
}