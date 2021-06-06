package com.reddit.app.features.search_reddit

import com.reddit.app.api.ApiServices
import com.reddit.app.data.vo.container.RedditApiResponse
import java.lang.Exception

class SearchRedRepository(
    private val apiServices: ApiServices
) : DataSource {
    override suspend fun searchReddit(
        query: String
    ): RedditApiResponse? {
        try {
            return apiServices.searchSubReddit(query, 1, "new", 30)
        } catch (ex: Exception) {
            return null
        }

    }

}
