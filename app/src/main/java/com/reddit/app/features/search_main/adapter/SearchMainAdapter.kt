package com.reddit.app.features.search_main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.ItemSearchRedditBinding
import com.reddit.app.extension.toAvatar
import com.reddit.app.utils.DiffUtilCallBack
import timber.log.Timber

class SearchMainAdapter(private val context: Context) : ListAdapter<RedditPost, SearchViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchRedditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, context)
    }
}

class SearchViewHolder(private val binding: ItemSearchRedditBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: RedditPost, context: Context) {
        val ran = (1..10).random()
        binding.apply {
            tvTitle.text = post.author_fullname
            tvDescription.text = post.title
            tvThumbsUp.text = post.ups.toString()
            tvRate.text = post.upvote_ratio.toString()
            ivRedditPost.toAvatar(ran, context)
        }
    }
}
