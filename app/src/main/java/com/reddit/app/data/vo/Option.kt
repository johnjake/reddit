package com.reddit.app.data.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    val id: String? = "",
    val text: String? = ""
) : Parcelable
