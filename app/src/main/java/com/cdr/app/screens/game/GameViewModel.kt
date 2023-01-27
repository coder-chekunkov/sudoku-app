package com.cdr.app.screens.game

import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.cdr.app.model.statistic.Statistic
import com.cdr.app.model.statistic.StatisticRepository
import com.cdr.app.utils.DbUnknownException
import com.cdr.core.navigator.Navigator
import com.cdr.core.uiactions.UiActions
import com.cdr.core.views.BaseViewModel
import com.cdr.sudoku.R
import kotlinx.coroutines.launch

class GameViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val statisticRepository: StatisticRepository
) : BaseViewModel() {


    fun exitGame(fragment: Fragment) {
        val directions = GameFragmentDirections.actionToRootFragment()
        uiActions.showAlertDialog(
            icon = R.drawable.ic_game_small,
            title = uiActions.getString(R.string.dialogTitle),
            message = uiActions.getString(R.string.dialogMessage),
            positiveButtonText = uiActions.getString(R.string.dialogButtonYes),
            negativeButtonText = uiActions.getString(R.string.dialogButtonNo),
            positiveAction = { navigator.launchByTopNavController(fragment, directions) }
        )
    }

    fun launchResultScreen(fragment: Fragment) {
        val statistic = Statistic(
            resultId = 1,
            difficultId = 3,
            mistakes = 1,
            points = 7412,
            elapsedTime = "01:15"
        )

        insertNewGameStatisticInDatabase(statistic)
        val directions = GameFragmentDirections.actionGameFragmentToResultFragment(statistic)
        navigator.launchByTopNavController(fragment, directions)
    }

    private fun insertNewGameStatisticInDatabase(statistic: Statistic) {
        viewModelScope.launch {
            try {
                statisticRepository.insertNewGameStatisticInfo(statistic)
            } catch (e: DbUnknownException) {
                println("Error!")
            }
        }
    }
}
