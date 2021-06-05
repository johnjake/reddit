package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mp4(
    val resolutions: List<Resolution>?,
    val source: Source?
) : Parcelable
