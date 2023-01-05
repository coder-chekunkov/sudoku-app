package com.cdr.app.model

import android.content.Context
import com.cdr.sudoku.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.random.Random

class FactsService(private val appContext: Context) {

    private val facts by lazy { createFacts() }

    fun getRandomFact(): String = facts[Random.nextInt(1, 17)]

    private fun createFacts(): MutableList<String> {
        val buffList = mutableListOf<String>()
        val jsonText = readText(appContext, R.raw.facts) // Получение значений из файла

        val jsonRoot = JSONObject(jsonText) // Root-элемент файла с фактами
        for (id in 1..17) {
            val jsonObject = jsonRoot.getJSONObject(id.toString()) // Факт по рандомную числу
            buffList.add(jsonObject.getString("fact"))
        }

        return buffList
    }

    // Чтение JSON-файла:
    private fun readText(context: Context, resId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resId)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val file = StringBuilder()
        var s: String?
        while (bufferedReader.readLine().also { s = it } != null) {
            file.append(s)
            file.append("\n")
        }
        return file.toString()
    }
}