package com.cdr.sudoku.model

import android.content.Context
import android.content.SharedPreferences

class StatisticService(private val context: Context, private val difficultID: Int) {

    fun setStatisticInfo(newStatistic: StatisticInfo) {
        val statisticFile = createStatisticFile()
        val editor = statisticFile.edit()

        with(editor) {
            putInt(ALL_GAMES, newStatistic.allGames)
            putInt(WIN_GAMES, newStatistic.allGames)
            putInt(LOST_GAMES, newStatistic.lostGames)
            putInt(WITHOUT_MISTAKES_GAMES, newStatistic.withoutMistakesGames)
            putString(BEST_TIME, newStatistic.bestTime)
            putString(AVERAGE_TIME, newStatistic.averageTime)
            putInt(BEST_POINTS, newStatistic.bestPoints)
            putInt(AVERAGE_POINTS, newStatistic.averagePoints)
        }

        editor.apply()
    }

    fun getStatisticInfo(): StatisticInfo {
        val statisticFile = createStatisticFile()
        return StatisticInfo(
            allGames = statisticFile.getInt(ALL_GAMES, 0),
            winGames = statisticFile.getInt(WIN_GAMES, 0),
            lostGames = statisticFile.getInt(LOST_GAMES, 0),
            withoutMistakesGames = statisticFile.getInt(WITHOUT_MISTAKES_GAMES, 0),
            bestTime = statisticFile.getString(BEST_TIME, "00:00").toString(),
            averageTime = statisticFile.getString(AVERAGE_TIME, "00:00").toString(),
            bestPoints = statisticFile.getInt(BEST_POINTS, 0),
            averagePoints = statisticFile.getInt(AVERAGE_POINTS, 0)
        )
    }

    private fun createStatisticFile(): SharedPreferences = when (difficultID) {
        DIFFICULTY_EASY -> context.getSharedPreferences(EASY_FILE, Context.MODE_PRIVATE)
        DIFFICULTY_MIDDLE -> context.getSharedPreferences(MIDDLE_FILE, Context.MODE_PRIVATE)
        DIFFICULTY_HARD -> context.getSharedPreferences(HARD_FILE, Context.MODE_PRIVATE)
        DIFFICULTY_EXPERT -> context.getSharedPreferences(EXPERT_FILE, Context.MODE_PRIVATE)
        else -> throw IllegalArgumentException("Error With Difficult ID")
    }

    companion object {
        private const val DIFFICULTY_EASY: Int = 1
        private const val DIFFICULTY_MIDDLE: Int = 2
        private const val DIFFICULTY_HARD: Int = 3
        private const val DIFFICULTY_EXPERT: Int = 4

        private const val EASY_FILE = "EASY_FILE_NAME"
        private const val MIDDLE_FILE = "MIDDLE_FILE_NAME"
        private const val HARD_FILE = "HARD_FILE_NAME"
        private const val EXPERT_FILE = "EXPERT_FILE_NAME"

        private const val ALL_GAMES = "ALL_GAMES"
        private const val WIN_GAMES = "WIN_GAMES"
        private const val LOST_GAMES = "LOST_GAMES"
        private const val WITHOUT_MISTAKES_GAMES = "WITHOUT_MISTAKES_GAMES"
        private const val BEST_TIME = "BEST_TIME"
        private const val AVERAGE_TIME = "AVERAGE_TIME"
        private const val BEST_POINTS = "BEST_POINTS"
        private const val AVERAGE_POINTS = "AVERAGE_POINTS"
    }
}

data class StatisticInfo(
    val allGames: Int,
    val winGames: Int,
    val lostGames: Int,
    val withoutMistakesGames: Int,
    val bestTime: String,
    val averageTime: String,
    val bestPoints: Int,
    val averagePoints: Int
)