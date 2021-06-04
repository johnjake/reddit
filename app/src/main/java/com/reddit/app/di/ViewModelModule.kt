package com.reddit.app.di

import com.reddit.app.features.main.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ViewModel(repository = get()) }
}
