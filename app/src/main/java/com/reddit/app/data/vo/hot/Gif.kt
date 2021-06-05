package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gif(
    val resolutions: List<Resolution>?,
    val source: Source? = null
) : Parcelable
