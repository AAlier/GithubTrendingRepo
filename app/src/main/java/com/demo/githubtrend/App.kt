package com.demo.githubtrend

import android.app.Application
import com.demo.githubtrend.common.network.NetworkModule
import com.demo.githubtrend.common.storage.StorageModule
import com.demo.githubtrend.feed.FeedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        GlobalContext.stopKoin()
        startKoin {
            androidContext(this@App)
            printLogger(Level.ERROR)
            allowOverride(true)
            modules(
                NetworkModule.create(),
                StorageModule.create(),
                FeedModule.create(),
            )
        }
    }
}
