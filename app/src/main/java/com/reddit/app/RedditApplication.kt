package com.reddit.app

import android.app.Application
import android.content.Context
import com.reddit.app.di.networkModule
import com.reddit.app.di.repositoryModule
import com.reddit.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RedditApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RedditApplication)
            modules(listOf(
                networkModule,
                repositoryModule,
                viewModelModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    companion object {
        lateinit var appContext: Context
    }
}
