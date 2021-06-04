package com.reddit.app.data.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Children(
    val data: PostDetails,
    val kind: String? = ""
) : Parcelable
