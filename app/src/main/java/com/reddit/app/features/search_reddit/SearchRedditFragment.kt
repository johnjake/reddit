package com.reddit.app.features.search_reddit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.R
import com.reddit.app.data.vo.State
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.FragmentSearchRedditBinding
import com.reddit.app.extension.toast
import com.reddit.app.features.search_reddit.adapter.SearchRedAdapter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.lang.Exception

class SearchRedditFragment : Fragment() {
    private var binding: FragmentSearchRedditBinding? = null
    private val bind get() = binding
    private var searchJob: Job? = null
    private val viewModel: SearchRedViewModel by inject()
    private val searchAdapter: SearchRedAdapter by lazy { context?.let { SearchRedAdapter(it) }!! }
    private var isLoading = MutableSharedFlow<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchRedditBinding.inflate(inflater, container, false)
        return bind?.root
    }

    @FlowPreview
    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query = savedInstanceState?.getString(last_query) ?: default_title
        initialLoading()
        observerLoadingData()
        initializedSearchView(view)
        observerSearchResult()
        searchQuery(query)
        initializedSearch(query)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(last_query, binding?.tvSearch?.text?.trim().toString())
    }

    private fun initializedSearchView(view: View) {
        val resultLayout = LinearLayoutManager(view.context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding?.apply {
            listSearch.apply {
                layoutManager = resultLayout
                adapter = searchAdapter
            }
        }
    }

    private fun observerSearchResult() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
           try {
               viewModel.dataState.collectLatest { state ->
                   handleStreamState(state)
               }
           } catch (ex: Exception) {
               lifecycleScope.launch {
                   isLoading.emit(false)
               }
               activity?.toast("No result found for ")
           }
        }
    }

    private fun handleStreamState(state: State<List<RedditPost>?>) {
        when(state) {
            is State.Data -> handleSuccessResult(state.data)
            is State.Error -> handleFailedResult(state.error)
            else -> Timber.e("Query Resulted some error")
        }
    }

    private fun handleFailedResult(error: Throwable) {
        Timber.e("Error: ${error.message}")
    }

    private fun handleSuccessResult(data: List<RedditPost>?) {
        if(data?.size ?: 0 > 0) {
            searchAdapter.submitList(data?.toMutableList())
            lifecycleScope.launch {
                isLoading.emit(false)
            }
        } else {
            activity?.toast("No result found!")
        }
    }

    @ExperimentalPagingApi
    private fun initializedSearch(query: String) {
        binding?.tvSearch?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                initialLoading()
            }
            override fun afterTextChanged(s: Editable?) {
                if(s?.isNotEmpty() == true) {
                    if(s.toString().length >= 5)
                    searchQuery(s.toString())
                }
            }
        })
    }

    @ExperimentalPagingApi
    private fun searchQuery(query: String) {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchReddit(query)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> showAnimation(R.raw.loading)
            false -> hideAnimation()
        }
    }

    private fun hideAnimation() {
        binding?.progressLoader?.visibility = View.GONE
    }

    private fun showAnimation(animationResource: Int) {
        binding?.progressLoader?.visibility = View.VISIBLE
        binding?.progressLoader?.setAnimation(animationResource)
        binding?.progressLoader?.playAnimation()
    }

    private fun initialLoading() {
        lifecycleScope.launchWhenStarted {
            isLoading.emit(true)
        }
    }

    @FlowPreview
    private fun observerLoadingData() {
        lifecycleScope.launch {
            isLoading.distinctUntilChanged().collect { load ->
                showLoading(load)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchJob?.cancel()
    }

    companion object {
        private const val last_query: String = "last_search_query"
        private const val default_title = "news"
    }
}
