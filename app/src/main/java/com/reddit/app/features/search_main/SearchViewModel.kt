package com.reddit.app.features.search_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.app.data.vo.State
import com.reddit.app.data.vo.container.RedditApiResponse
import com.reddit.app.data.vo.container.RedditPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    private var stateJob: Job? = null
    private var mutableList = mutableListOf<RedditPost>()
    private val dataFlow = MutableSharedFlow<State<List<RedditPost>?>>()
    val dataState: SharedFlow<State<List<RedditPost>?>> = dataFlow

    fun searchReddit(query: String) {
        mutableList.clear()
        stateJob = viewModelScope.launch(Dispatchers.IO) {
            repository.searchReddit(query).data.children.forEach {
                mutableList.add(it.data)
            }
            val stateData = State.Data(mutableList)
            dataFlow.emit(stateData)
        }
    }
}
