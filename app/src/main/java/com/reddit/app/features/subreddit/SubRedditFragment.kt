package com.reddit.app.features.subreddit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.R
import com.reddit.app.data.vo.Children
import com.reddit.app.databinding.FragmentSubredditBinding
import com.reddit.app.features.feeds.adapter.RedditPostAdapter
import com.reddit.app.features.subreddit.adapter.SubAdapter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class SubRedditFragment : Fragment() {
    private var binding: FragmentSubredditBinding? = null
    private val bind get() = binding
    private val mainModel: SubViewModel by inject()
    private val redAdapter: SubAdapter by lazy { context?.let { SubAdapter(it) }!! }
    private var isLoading = MutableSharedFlow<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubredditBinding.inflate(inflater, container, false)
        return bind?.root
    }

    @FlowPreview
    override fun onStart() {
        super.onStart()
        observerLoadingData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            isLoading.emit(true)
        }
        observeLiveData()
        initializeList(view)
    }

    @FlowPreview
    private fun observerLoadingData() {
        lifecycleScope.launch {
            isLoading.distinctUntilChanged().collect { load ->
                showLoading(load)
            }
        }
    }

    private fun observeLiveData() {
        mainModel.getPosts().observe(viewLifecycleOwner, Observer {
            redAdapter.submitList(it)
            if(it.size > 0) {
                Thread.sleep(800)
                lifecycleScope.launch {
                    isLoading.emit(false)
                }
            }
        })
    }

    private fun initializeList(view: View) {
        binding?.apply {
            subRedditList.layoutManager = LinearLayoutManager(context)
            subRedditList.adapter = redAdapter
           /* searchButton.setOnClickListener {
                view.findNavController().navigate(R.id.action_search_main)
            } */
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
}