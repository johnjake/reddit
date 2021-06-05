package com.reddit.app.di

import com.reddit.app.features.feeds.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory { Repository(api = get()) }
}
