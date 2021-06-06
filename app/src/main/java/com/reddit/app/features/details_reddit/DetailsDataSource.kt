package com.reddit.app.features.details_reddit

import androidx.paging.PageKeyedDataSource
import com.reddit.app.api.ApiServices
import com.reddit.app.data.vo.container.RedditPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

@Suppress("DEPRECATION")
class DetailsDataSource(coroutineContext: CoroutineContext, private val apiServices: ApiServices) :
    PageKeyedDataSource<String, RedditPost>() {
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditPost>
    ) {
        scope.launch {
            try {
                val response =
                    apiServices.fetchPosts(loadSize = params.requestedLoadSize, after = params.key)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val items = listing?.children?.map { it.data }
                        callback.onResult(items ?: listOf(), listing?.after)
                    }
                }

            }catch (exception : Exception){
                Timber.e("PostsDataSource Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditPost>
    ) {
        scope.launch {
            try {
                val response =
                    apiServices.fetchPosts(loadSize = params.requestedLoadSize, before = params.key)
                when{
                    response.isSuccessful -> {
                        val listing = response.body()?.data
                        val items = listing?.children?.map { it.data }
                        callback.onResult(items ?: listOf(), listing?.after)
                    }
                }
            }catch (exception : Exception){
                Timber.e("PostsDataSource Failed to fetch data!")
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditPost>
    ) {
        scope.launch {
            //try {
            val response = apiServices.fetchPosts(loadSize = params.requestedLoadSize)
            when{
                response.isSuccessful -> {
                    val listing = response.body()?.data
                    val redditPosts = listing?.children?.map { it.data }
                    callback.onResult(redditPosts ?: listOf(), listing?.before, listing?.after)
                }
            }

            // }catch (exception : Exception){
            //Timber.e("PostsDataSource Failed to fetch data!")
            // }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}
