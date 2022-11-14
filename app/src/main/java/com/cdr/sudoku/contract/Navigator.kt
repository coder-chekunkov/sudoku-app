package com.cdr.sudoku.contract

import androidx.fragment.app.Fragment

interface Navigator {
    fun showHistoryFragment()
    fun showGameFragment()
    fun showStatisticFragment()
}

fun Fragment.navigator(): Navigator = requireActivity() as Navigator