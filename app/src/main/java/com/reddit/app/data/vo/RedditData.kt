package com.reddit.app.data.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class RedditData(
    val `data`: ParentData,
    val kind: String? = ""
) : Parcelable
