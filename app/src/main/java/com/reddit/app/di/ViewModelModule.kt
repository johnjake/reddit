package com.reddit.app.di

import com.reddit.app.features.feeds.MainViewModel
import com.reddit.app.features.feeds.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ViewModel(repository = get()) }
    viewModel { MainViewModel(api = get()) }
}
