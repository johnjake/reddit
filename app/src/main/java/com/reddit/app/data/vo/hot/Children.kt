package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Children(
    val `data`: RedditPostDetails,
    val kind: String?
) : Parcelable
