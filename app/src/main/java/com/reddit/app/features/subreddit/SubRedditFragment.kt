package com.reddit.app.features.subreddit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.R
import com.reddit.app.databinding.FragmentSubredditBinding
import com.reddit.app.extension.showNavigation
import com.reddit.app.features.main.RedditMainActivity
import com.reddit.app.features.subreddit.adapter.SubAdapter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SubRedditFragment : Fragment() {
    private var binding: FragmentSubredditBinding? = null
    private val bind get() = binding
    private val mainModel: SubViewModel by inject()
    private val redAdapter: SubAdapter by lazy { context?.let { SubAdapter(it) { reddit -> onClickListener(reddit) } }!! }
    private var isLoading = MutableSharedFlow<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubredditBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            isLoading.emit(true)
        }
        observerListData()
        initializeList(view)
    }

    @FlowPreview
    override fun onStart() {
        super.onStart()
        observerLoadingData()
    }

    override fun onResume() {
        super.onResume()
        bottomVisibility()
    }

    @FlowPreview
    private fun observerLoadingData() {
        lifecycleScope.launch {
            isLoading.distinctUntilChanged().collect { load ->
                showLoading(load)
            }
        }
    }

    private fun observerListData() {
        mainModel.getPosts().observe(viewLifecycleOwner, Observer {
            redAdapter.submitList(it)
            if(it.size > 0) {
                Thread.sleep(200)
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
            searchButton.setOnClickListener {
               view.findNavController().navigate(R.id.action_search_reddit)
            }
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

    private fun onClickListener(subReddit: String) {
        val parameter = SubRedditFragmentDirections.actionDetailsReddit(subReddit)
        RedditMainActivity.onBackPress.value = true
        view?.findNavController()?.navigate(parameter)
    }

    private fun bottomVisibility() {
        if (RedditMainActivity.onBackPress.value) {
            RedditMainActivity.onBackPress.value = false
            activity.showNavigation()
        }
    }
}
