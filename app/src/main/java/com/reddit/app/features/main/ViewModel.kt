package com.reddit.app.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.app.data.vo.Children
import com.reddit.app.data.vo.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ViewModel(
    private val repository: Repository
) : ViewModel() {

    private var stateJob: Job? = null
    private val dataFlow = MutableSharedFlow<State<List<Children>?>>()
    val dataState: SharedFlow<State<List<Children>?>> = dataFlow

    fun getNewPost(sortKey: String) {
        stateJob = viewModelScope.launch(Dispatchers.Main) {
            val data = repository.getSubReddit(sortKey)
            val stateData = State.Data(data)
            dataFlow.emit(stateData)
        }
    }
}
