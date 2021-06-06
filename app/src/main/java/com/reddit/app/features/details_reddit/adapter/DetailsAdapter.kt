package com.reddit.app.features.details_reddit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddit.app.data.vo.container.RedditPost
import com.reddit.app.databinding.ItemDetailsRedditBinding
import com.reddit.app.extension.toAvatar

import com.reddit.app.utils.DiffUtilCallBack

@Suppress("DEPRECATION")
class DetailsAdapter(private val con: Context) : PagedListAdapter<RedditPost, DetailsAdapter.PostViewHolder>(DiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemDetailsRedditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it, con) }
    }

    class PostViewHolder(private val binding : ItemDetailsRedditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: RedditPost, context: Context) {
            val randomNum = (1..15).random()
            binding.apply {
                tvThumbsDown.text = post.downs.toString()
                tvThumbsUp.text = post.ups.toString()
                tvUsername.text = post.author_fullname
                tvStarCounter.text = post.score.toString()
                tvCounterComment.text = post.num_comments.toString()
               tvContent.text = post.title
                avatar.toAvatar(randomNum, context)
            }
        }
    }
}
