package com.reddit.app.di

import com.reddit.app.features.details_reddit.DetailsViewModel
import com.reddit.app.features.feeds.FeedViewModel
import com.reddit.app.features.feeds.ViewModel
import com.reddit.app.features.search_main.SearchViewModel
import com.reddit.app.features.subreddit.SubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ViewModel(repository = get()) }
    viewModel { FeedViewModel(api = get()) }
    viewModel { SearchViewModel(repository = get()) }
    viewModel { SubViewModel(api = get()) }
    viewModel { DetailsViewModel(api = get()) }
}
