package com.reddit.app.features.search_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reddit.app.databinding.FragmentSearchMainBinding

class SearchMainFragment : Fragment() {
    private var binding: FragmentSearchMainBinding? = null
    private val bind get() = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMainBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
