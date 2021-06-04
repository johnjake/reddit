package com.reddit.app.di

import com.reddit.app.RedditApplication
import org.koin.dsl.module

val contextModule = module {
    single { provideAndroidContext() }
}

fun provideAndroidContext(): RedditApplication {
   return RedditApplication()
}
