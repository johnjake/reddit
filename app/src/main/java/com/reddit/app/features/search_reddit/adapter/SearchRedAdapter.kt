package com.reddit.app.features.search_reddit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.ItemSearchSubredditBinding
import com.reddit.app.extension.toAvatar
import com.reddit.app.utils.DiffUtilCallBack

class SearchRedAdapter(private val context: Context) : ListAdapter<RedditPost, SearchViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchSubredditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, context)
    }
}

class SearchViewHolder(private val binding: ItemSearchSubredditBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: RedditPost, context: Context) {
        val randomNum = (1..15).random()
        binding.apply {
            tvTitle.text = post.subreddit_name_prefixed
            tvReddit.text = post.title
            avatar.toAvatar(randomNum, context)
        }
    }
}
