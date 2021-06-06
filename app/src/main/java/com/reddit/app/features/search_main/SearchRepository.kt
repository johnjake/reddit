package com.reddit.app.features.search_main

import com.reddit.app.api.ApiServices
import com.reddit.app.data.vo.container.RedditApiResponse

class SearchRepository(
    private val apiServices: ApiServices
) : DataSource {
    override suspend fun searchReddit(
        query: String
    ): RedditApiResponse {
        return apiServices.searchReddit(query, 1, "new", 30)
    }

}
