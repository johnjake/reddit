package com.reddit.app.features.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reddit.app.databinding.FragmentRedditChatBinding
import com.reddit.app.utils.RedditDialog

class ChattingFragment : Fragment() {
    private var binding: FragmentRedditChatBinding? = null
    private val bind get() = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRedditChatBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.save?.setOnClickListener {
            RedditDialog.build(it.context, "Under Construction", "Sorry Chat services is under construction")
        }
    }
}
