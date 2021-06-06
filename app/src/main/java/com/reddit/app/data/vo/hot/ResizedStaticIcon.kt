package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResizedStaticIcon(
    val height: Int? = 0,
    val url: String? = "",
    val width: Int? = 0
) : Parcelable
