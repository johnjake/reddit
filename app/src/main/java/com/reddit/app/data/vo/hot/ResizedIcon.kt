package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResizedIcon(
    val height: Int?,
    val url: String?,
    val width: Int?
) : Parcelable
