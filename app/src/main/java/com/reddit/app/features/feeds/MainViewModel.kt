package com.reddit.app.features.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.reddit.app.api.ApiServices
import com.reddit.app.data.vo.container.RedditPost
import kotlinx.coroutines.Dispatchers

@Suppress("DEPRECATION")
class MainViewModel(private val api: ApiServices) : ViewModel() {
    var postsLiveData  : LiveData<PagedList<RedditPost>>
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<RedditPost>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, RedditPost> {

        val dataSourceFactory = object : DataSource.Factory<String, RedditPost>() {
            override fun create(): DataSource<String, RedditPost> {
                return PostsDataSource(Dispatchers.IO, api)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
