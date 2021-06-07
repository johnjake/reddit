package com.reddit.app.features.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reddit.app.databinding.FragmentRedditInboxBinding
import com.reddit.app.utils.RedditDialog

class InboxFragment : Fragment() {
    private var binding: FragmentRedditInboxBinding? = null
    private val bind get() = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRedditInboxBinding.inflate(inflater, container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.save?.setOnClickListener {
             RedditDialog.build(it.context, "Under Construction", "Sorry Inbox services is under construction")
        }
    }
}
