package com.reddit.app.features.feeds.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.R
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.ItemRedditPostBinding
import com.reddit.app.extension.toAvatar
import com.reddit.app.extension.toSubReddit

import com.reddit.app.utils.DiffUtilCallBack

@Suppress("DEPRECATION")
class RedditPostAdapter(private val con: Context) : PagedListAdapter<RedditPost, RedditPostAdapter.PostViewHolder>(DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemRedditPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it, con) }
    }

    override fun getItemCount(): Int {
        dataSize = super.getItemCount()
        return dataSize
    }

    companion object {
        var dataSize: Int = 0
    }

    class PostViewHolder(private val binding : ItemRedditPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: RedditPost, context: Context) {
            val randomNum = (1..15).random()
            upvoteCriteria(post.ups ?: 0)
            binding.apply {
                tvThumbsDown.text = post.downs.toString()
                tvThumbsUp.text = post.ups.toString()
                tvUsername.text = post.author_fullname
                tvStarCounter.text = post.score.toString()
                tvCounterComment.text = post.num_comments.toString()
                tvContent.text = post.title
                avatar.toAvatar(randomNum, context)
                imageFeed.toSubReddit(randomNum, context)
            }
        }
        @SuppressLint("SetTextI18n")
        private fun upvoteCriteria(upvote: Int) {
            when {
                upvote == 0 -> binding.tvComment.text = " No Upvote size: $dataSize"
                upvote > 5 -> binding.tvComment.text = "Great Post size: $dataSize "
                upvote <= 5 -> binding.tvComment.text = "Less than 5 size: $dataSize"
            }
        }
    }
}
