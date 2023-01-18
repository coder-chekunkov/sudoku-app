package com.cdr.app.screens.result

import androidx.fragment.app.Fragment
import com.cdr.app.screens.game.GameFragmentDirections
import com.cdr.core.navigator.Navigator
import com.cdr.core.views.BaseViewModel

class ResultViewModel(private val navigator: Navigator) : BaseViewModel() {

    fun launchHomeScreen(fragment: Fragment) {
        val directions = GameFragmentDirections.actionToRootFragment()
        navigator.launchByTopNavController(fragment, directions)
    }
}