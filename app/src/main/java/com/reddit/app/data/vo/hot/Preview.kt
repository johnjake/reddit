package com.reddit.app.data.vo.hot

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Preview(
    val enabled: Boolean?,
    val images: List<Image>?,
    val reddit_video_preview: RedditVideoPreview?
) : Parcelable
