package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: String?,
    val resolutions: List<Resolution>?,
    val source: Source?,
    val variants: Variants?
) : Parcelable
