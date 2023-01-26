package com.cdr.app

import android.content.Context
import com.cdr.app.model.facts.FactsService

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    val factService: FactsService by lazy { FactsService(applicationContext) }
}