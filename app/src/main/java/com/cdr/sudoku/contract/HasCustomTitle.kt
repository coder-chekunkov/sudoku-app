package com.cdr.sudoku.contract

import androidx.annotation.StringRes

interface HasCustomTitle {
    @StringRes
    fun getResTitle(): Int
}