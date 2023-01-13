package com.cdr.app.screens.game

import androidx.fragment.app.Fragment
import com.cdr.core.navigator.Navigator
import com.cdr.core.uiactions.UiActions
import com.cdr.core.views.BaseViewModel
import com.cdr.sudoku.R

class GameViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions
) : BaseViewModel() {


    fun exitGame(fragment: Fragment) {
        val directions = GameFragmentDirections.actionGameFragmentToRootFragment()
        uiActions.showAlertDialog(
            icon = R.drawable.ic_game_small,
            title = uiActions.getString(R.string.dialogTitle),
            message = uiActions.getString(R.string.dialogMessage),
            positiveButtonText = uiActions.getString(R.string.dialogButtonYes),
            negativeButtonText = uiActions.getString(R.string.dialogButtonNo),
            positiveAction = { navigator.launchByTopNavController(fragment, directions) }
        )
    }


}
