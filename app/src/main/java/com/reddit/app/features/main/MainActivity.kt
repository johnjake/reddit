package com.reddit.app.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.reddit.app.R
import com.reddit.app.data.vo.Children
import com.reddit.app.data.vo.State
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            viewModel.dataState.collect { state ->
                handleSuccessState(state)
            }
        }

    }

    private fun handleSuccessState(state: State<List<Children>?>) {
        when(state) {
            is State.Data -> handleResult(state.data)
            is State.Error -> handleError(state.error)
            else -> Timber.e("An error occurred during query request!")
        }
    }

    private fun handleError(error: Throwable) {
        Timber.e("Error Throw Exception: ${error.message}")
    }

    private fun handleResult(data: List<Children>?) {
        data?.forEach {
            Timber.d("Title: ${it.data.title}")
        }
    }


    override fun onStart() {
        super.onStart()
        viewModel.getNewPost("new")
    }

}
