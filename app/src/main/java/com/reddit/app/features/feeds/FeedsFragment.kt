package com.reddit.app.features.feeds

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.R
import com.reddit.app.databinding.FeedsFragmentBinding
import com.reddit.app.extension.showNavigation
import com.reddit.app.features.feeds.adapter.RedditPostAdapter
import com.reddit.app.features.main.RedditMainActivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FeedsFragment : Fragment() {
    private var binding: FeedsFragmentBinding? = null
    private val bind get() = binding
    private val mainModel: FeedViewModel by inject()
    private val redAdapter: RedditPostAdapter by lazy { RedditPostAdapter(context) { link -> onClickListener(link) } }
    private var isLoading = MutableSharedFlow<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FeedsFragmentBinding.inflate(inflater, container, false)
        return bind?.root
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
                Thread.sleep(200)
                lifecycleScope.launch {
                    isLoading.emit(false)
                }
            }
        })
    }

    private fun initializeList(view: View) {
        binding?.apply {
            feedList.layoutManager = LinearLayoutManager(context)
            feedList.adapter = redAdapter
            searchButton.setOnClickListener {
                view.findNavController().navigate(R.id.action_search_main)
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

    private fun bottomVisibility() {
        if (RedditMainActivity.onBackPress.value) {
            RedditMainActivity.onBackPress.value = false
            activity.showNavigation()
        }
    }

    private fun onClickListener(link: String) {
        val arg = FeedsFragmentDirections.actionFeedsWeb(link)
        view?.findNavController()?.navigate(arg)
    }
}
