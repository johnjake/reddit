package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Variants(
    val gif: Gif?,
    val mp4: Mp4?
) : Parcelable
