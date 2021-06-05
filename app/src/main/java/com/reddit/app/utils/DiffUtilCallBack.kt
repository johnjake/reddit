package com.reddit.app.utils

import androidx.recyclerview.widget.DiffUtil
import com.reddit.app.data.vo.container.RedditPost

class DiffUtilCallBack : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.title == newItem.title
                && oldItem.score == newItem.score
                && oldItem.num_comments == newItem.num_comments
    }

}
