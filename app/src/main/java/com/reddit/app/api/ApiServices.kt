package com.reddit.app.api

import com.reddit.app.data.vo.RedditData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("subreddit/new.json")
    suspend fun getSubReddit(
        @Query("sort") sortKey: String
    ): RedditData
}
