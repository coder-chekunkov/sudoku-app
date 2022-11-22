package com.cdr.sudoku.contract

import androidx.fragment.app.Fragment

interface Navigator {
    fun showHistoryFragment() // Fragment с историей игр
    fun showLaunchGameFragment() // Fragment с запуском игры (выбор сложности и т.д.)
    fun showStatisticFragment() // Fragment со статистикой
    fun showGameFragment(diff: Int) // Fragment с игрой
    fun showResultFragment(diff: Int, mis: Int, points: Int, time: Int, res: Boolean)
    fun showInfoSettings() // Fragment с информацией
}

fun Fragment.navigator(): Navigator = requireActivity() as Navigator