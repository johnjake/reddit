package com.reddit.app.features.subreddit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.ItemSubredditBinding
import com.reddit.app.extension.toAvatar
import com.reddit.app.utils.DiffUtilCallBack

@Suppress("DEPRECATION")
class SubAdapter(
    private val con: Context,
    private val itemClickListener: (subReddit: String) -> Unit
) : PagedListAdapter<RedditPost, SubAdapter.PostViewHolder>(DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemSubredditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it, con) }
    }

   inner class PostViewHolder(private val binding : ItemSubredditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: RedditPost, context: Context) {
            val randomNum = (1..15).random()
            binding.apply {
                tvTitle.text = post.subreddit
                tvReddit.text = post.subreddit_name_prefixed
                avatar.toAvatar(randomNum, context)
                constrainReddit.setOnClickListener {
                    itemClickListener(post.subreddit ?: "")
                }
            }
        }
    }
}
