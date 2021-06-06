package com.reddit.app.features.feeds

import com.reddit.app.api.ApiServices
import com.reddit.app.data.vo.Children

class Repository(private val api: ApiServices) : DataSource {
    override suspend fun getSubReddit(sortKey: String): List<Children>? {
       return api.getSubReddit(sortKey).data.children
    }
}
