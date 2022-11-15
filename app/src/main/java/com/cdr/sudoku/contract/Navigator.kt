package com.cdr.sudoku.contract

import androidx.fragment.app.Fragment

interface Navigator {
    fun showHistoryFragment() // Fragment с историей игр
    fun showLaunchGameFragment() // Fragment с запуском игры (выбор сложности и т.д.)
    fun showStatisticFragment() // Fragment со статистикой
    fun showGameFragment(diff: Int) // Fragment с игрой
    fun showResultFragment(diff: Int, res: Boolean) // Fragment с результатом игры
}

fun Fragment.navigator(): Navigator = requireActivity() as Navigator