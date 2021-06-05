package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DefaultData(
    val `data`: Data?,
    val kind: String?
) : Parcelable
