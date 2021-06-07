package com.reddit.app.features.details_reddit.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.ItemDetailsRedditBinding
import com.reddit.app.extension.toAvatar
import com.reddit.app.extension.toSubReddit
import com.reddit.app.features.feeds.adapter.RedditPostAdapter

import com.reddit.app.utils.DiffUtilCallBack

@Suppress("DEPRECATION")
class DetailsAdapter(
    private val con: Context?,
    private val itemClickListener: (url: String) -> Unit
) : PagedListAdapter<RedditPost, DetailsAdapter.PostViewHolder>(DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemDetailsRedditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { con?.let { it1 -> holder.bind(it, it1) } }
    }

    override fun getItemCount(): Int {
        dataSize = super.getItemCount()
        return dataSize
    }

    companion object {
        var dataSize: Int = 0
    }

    inner class PostViewHolder(private val binding : ItemDetailsRedditBinding) : RecyclerView.ViewHolder(binding.root) {
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
                imageDetails.toSubReddit(randomNum, context)
                imageDetails.setOnClickListener {
                    itemClickListener(post.permalink ?: "")
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun upvoteCriteria(upvote: Int) {
            when {
                upvote == 0 -> binding.tvComment.text = " No Upvote - list size: $dataSize"
                upvote > 5 -> binding.tvComment.text = "Great Post - list size: $dataSize "
                upvote <= 5 -> binding.tvComment.text = "Less than 5 - list size: $dataSize"
            }
        }
    }
}
