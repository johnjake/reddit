package com.reddit.app.features.main

import com.reddit.app.data.vo.Children

interface DataSource {
    suspend fun getSubReddit(sortKey: String): List<Children>?
}
