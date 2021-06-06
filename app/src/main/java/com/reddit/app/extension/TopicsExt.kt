package com.reddit.app.extension

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.reddit.app.R

fun ImageView.toSubReddit(userId: Int, context: Context) {
    when(userId) {
        1 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_1))
        2 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_2))
        3 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_3))
        4 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_4))
        5 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_5))
        6 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_6))
        7 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_7))
        8 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_8))
        9 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_9))
        10 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_10))
        11 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_11))
        12 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_12))
        13 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_13))
        14 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_14))
        15 -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_15))
        else -> this.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reddit_16))
    }
}
