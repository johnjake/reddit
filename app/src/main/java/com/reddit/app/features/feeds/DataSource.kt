package com.reddit.app.features.feeds

import com.reddit.app.data.vo.Children

interface DataSource {
    suspend fun getSubReddit(sortKey: String): List<Children>?
}
