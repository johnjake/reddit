package com.reddit.app.features.search_main

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
import com.reddit.app.data.vo.State
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.FragmentSearchMainBinding
import com.reddit.app.features.search_main.adapter.SearchMainAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class SearchMainFragment : Fragment() {
    private var binding: FragmentSearchMainBinding? = null
    private val bind get() = binding
    private var searchJob: Job? = null
    private val viewModel: SearchViewModel by inject()
    private val searchAdapter: SearchMainAdapter by lazy { context?.let { SearchMainAdapter(it) }!! }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMainBinding.inflate(inflater, container, false)
        return bind?.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query = savedInstanceState?.getString(last_query) ?: default_title
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
            viewModel.dataState.collectLatest { state ->
                 handleStreamState(state)
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
        searchAdapter.submitList(data?.toMutableList())
    }

    @ExperimentalPagingApi
    private fun initializedSearch(query: String) {
        binding?.tvSearch?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                if(s?.isNotEmpty() == true) {
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

    companion object {
        private const val last_query: String = "last_search_query"
        private const val default_title = "Star Wars"
    }
}
