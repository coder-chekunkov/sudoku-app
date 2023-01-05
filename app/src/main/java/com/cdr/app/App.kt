package com.cdr.app

import android.app.Application
import com.cdr.app.model.FactsService
import com.cdr.core.BaseApplication

class App : Application(), BaseApplication {

    override val models: List<Any> = listOf(
        FactsService(this)
    )
}