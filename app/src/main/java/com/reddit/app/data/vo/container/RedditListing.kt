package com.reddit.app.data.vo.container

data class RedditListing (
    val children: List<PostContainer>,
    val after: String?,
    val before: String?)
