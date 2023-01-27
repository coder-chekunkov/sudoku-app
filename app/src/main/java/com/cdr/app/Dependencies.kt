package com.cdr.app

import android.content.Context
import androidx.room.Room
import com.cdr.app.model.database.AppDatabase
import com.cdr.app.model.facts.FactsService
import com.cdr.app.model.statistic.StatisticDatabaseRepository

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "statistic.db")
            .createFromAsset("initial_database_sudoku.db")
            .build()
    }

    val factService: FactsService by lazy { FactsService(applicationContext) }
    val statisticRepository: StatisticDatabaseRepository by lazy { StatisticDatabaseRepository(appDatabase.getStatisticDao()) }
}