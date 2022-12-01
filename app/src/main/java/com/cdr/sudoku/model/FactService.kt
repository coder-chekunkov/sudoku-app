package com.cdr.sudoku.model

import android.content.Context
import com.cdr.sudoku.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.random.Random

class FactService {

    fun createFact(context: Context): String {
        val jsonText = readText(context, R.raw.facts) // Получение значений из файла
        val factId = Random.nextInt(1, 17)

        val jsonRoot = JSONObject(jsonText) // Root-элемент файла с фактами
        val jsonObject = jsonRoot.getJSONObject(factId.toString()) // Факт по рандомную числу

        return jsonObject.getString("fact")
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