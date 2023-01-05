package com.cdr.app.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_EASY
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_EXPERT
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_HARD
import com.cdr.app.screens.home.HomeViewModel.Companion.DIFFICULTY_MIDDLE
import com.cdr.core.uiactions.UiActions
import com.cdr.core.views.BaseViewModel
import com.cdr.sudoku.R

class GameViewModel(
    private val screen: GameFragment.Screen,
    private val uiActions: UiActions
) : BaseViewModel() {

    private val _difficult = MutableLiveData<String>()
    val difficult: LiveData<String> = _difficult

    init {
        _difficult.value = createDifficultLabel()
    }

    private fun createDifficultLabel(): String = when(screen.difficult) {
        DIFFICULTY_EASY -> uiActions.getString(R.string.difficultEasy)
        DIFFICULTY_MIDDLE -> uiActions.getString(R.string.difficultMiddle)
        DIFFICULTY_HARD -> uiActions.getString(R.string.difficultHard)
        DIFFICULTY_EXPERT -> uiActions.getString(R.string.difficultExpert)
        else -> "Error"
    }
}