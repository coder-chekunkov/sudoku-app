package com.cdr.app.screens.information

import androidx.navigation.NavController
import com.cdr.core.navigator.Navigator
import com.cdr.core.views.BaseViewModel

class InformationViewModel(private val navigator: Navigator) : BaseViewModel() {

    fun showHomeScreen(navController: NavController) =
        navigator.popBackStackByNavController(navController)
}