package com.reddit.app.api

import com.reddit.app.data.vo.RedditData
import com.reddit.app.data.vo.container.RedditApiResponse
import com.reddit.app.data.vo.hot.DefaultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("subreddit/new.json")
    suspend fun getSubReddit(
        @Query("sort") sortKey: String
    ): RedditData

    @GET("aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 30,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>
}
