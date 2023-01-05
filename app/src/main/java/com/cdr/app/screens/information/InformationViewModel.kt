package com.cdr.app.screens.information

import com.cdr.core.navigator.Navigator
import com.cdr.core.views.BaseViewModel

class InformationViewModel(private val navigator: Navigator) : BaseViewModel() {

    fun showHomeScreen() = navigator.goBack()
}