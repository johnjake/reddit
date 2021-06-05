package com.reddit.app.features.feeds

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.data.vo.Children
import com.reddit.app.data.vo.State
import com.reddit.app.databinding.FeedsFragmentBinding
import com.reddit.app.features.feeds.adapter.RedditPostAdapter
import org.koin.android.ext.android.inject
import timber.log.Timber

class FeedsFragment : Fragment() {
    private var binding: FeedsFragmentBinding? = null
    private val bind get() = binding
    private val viewModel: ViewModel by inject()
    private val mainModel: MainViewModel by inject()
    private val redAdapter: RedditPostAdapter by lazy { context?.let { RedditPostAdapter(it) }!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FeedsFragmentBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* lifecycleScope.launchWhenStarted {
            viewModel.dataState.collect { state ->
                handleSuccessState(state)
            }
        } */
        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        mainModel.getPosts().observe(viewLifecycleOwner, Observer {
            redAdapter.submitList(it)
        })
    }

    private fun initializeList() {

        binding?.apply {
            feedList.layoutManager = LinearLayoutManager(context)
            feedList.adapter = redAdapter
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
       // viewModel.getNewPost("new")
    }

}
