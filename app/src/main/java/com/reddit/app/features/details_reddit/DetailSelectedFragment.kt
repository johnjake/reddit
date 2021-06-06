package com.reddit.app.features.details_reddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reddit.app.databinding.FragmentDetailsRedditBinding

class DetailSelectedFragment : Fragment() {
    private var binding: FragmentDetailsRedditBinding? = null
    private val bind get() = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsRedditBinding.inflate(inflater, container, false)
        return bind?.root
    }
}
