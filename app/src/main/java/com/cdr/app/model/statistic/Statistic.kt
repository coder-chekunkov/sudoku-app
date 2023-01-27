package com.cdr.app.model.statistic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistic(
    val resultId: Long,
    val difficultId: Long,
    val mistakes: Long,
    val points: Long,
    val elapsedTime: String
) : Parcelable {

    companion object {
        const val WIN_RESULT_STATISTIC: Long = 1
    }
}