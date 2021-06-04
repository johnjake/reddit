package com.reddit.app.data.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParentData(
    val after: String? = "",
    val before: String? = "",
    val children: List<Children>? = emptyList(),
    val dist: Int? = 0,
    val modhash: String? = ""
) : Parcelable
