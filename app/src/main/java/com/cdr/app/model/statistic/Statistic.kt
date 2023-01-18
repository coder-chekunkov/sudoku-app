package com.cdr.app.model.statistic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistic(
    val result: Boolean,
    val difficult: Int,
    val mistakes: Int,
    val points: Int,
    val time: String
) : Parcelable