package com.reddit.app.features.search_main

import com.reddit.app.data.vo.container.RedditApiResponse

interface DataSource {
    suspend fun searchReddit(
        query: String
    ) : RedditApiResponse
}
