package com.reddit.app.features.details_reddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.app.R
import com.reddit.app.databinding.FragmentDetailsRedditBinding
import com.reddit.app.extension.hideNavigation
import com.reddit.app.features.details_reddit.adapter.DetailsAdapter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailSelectedFragment : Fragment() {
    private var binding: FragmentDetailsRedditBinding? = null
    private val bind get() = binding
    private val mainModel: DetailsViewModel by inject()
    private val redAdapter: DetailsAdapter by lazy { context?.let { DetailsAdapter(it) }!! }
    private var isLoading = MutableSharedFlow<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsRedditBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            isLoading.emit(true)
        }
        initializeList(view)

        binding?.backButton?.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    @FlowPreview
    override fun onStart() {
        super.onStart()
        observerLoadingData()
        arguments?.let { bundle ->
            val arg = DetailSelectedFragmentArgs.fromBundle(bundle)
            mainModel.search = arg.subreddit
            binding?.tvSubReddit?.text = arg.subreddit
        }
        observeLiveData()
        activity?.hideNavigation()
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
