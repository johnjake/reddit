package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val after: String,
    val before: String,
    val children: List<Children>?,
    val dist: Int?,
    val modhash: String?
) : Parcelable
