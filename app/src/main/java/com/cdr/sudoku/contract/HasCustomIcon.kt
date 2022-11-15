package com.cdr.sudoku.contract

import androidx.annotation.DrawableRes

interface HasCustomIcon {
    @DrawableRes
    fun getResIcon(): Int
}