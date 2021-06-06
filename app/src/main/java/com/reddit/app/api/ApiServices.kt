package com.reddit.app.api

import com.reddit.app.data.vo.RedditData
import com.reddit.app.data.vo.container.RedditApiResponse
import com.reddit.app.data.vo.hot.DefaultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("r/subreddit/new.json")
    suspend fun getSubReddit(
        @Query("sort") sortKey: String
    ): RedditData

    @GET("r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 30,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>

    @GET("hot.json")
    suspend fun fetchPostsReddit(
        @Query("limit") loadSize: Int = 30,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>

    @GET("{subreddit}/hot.json")
    suspend fun fetchDetails(
        @Query("subreddit") subTopic: String,
        @Query("limit") loadSize: Int = 30,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>

    @GET("search.json")
    suspend fun searchReddit(
        @Query("q") query: String,
        @Query("raw_json") rawJson: Int = 1,
        @Query("sort") sort: String = "new",
        @Query("limit") limit: Int = 30,
        @Query("include_over_18") includeRated: String = "on"
    ): RedditApiResponse
}
