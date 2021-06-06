package com.reddit.app.di

import com.reddit.app.features.feeds.Repository
import com.reddit.app.features.search_main.SearchRepository
import com.reddit.app.features.search_reddit.SearchRedRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { Repository(api = get()) }
    factory { SearchRepository(apiServices = get()) }
    factory { SearchRedRepository(apiServices = get()) }
}
