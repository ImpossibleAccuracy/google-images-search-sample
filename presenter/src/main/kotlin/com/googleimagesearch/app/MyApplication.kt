package com.googleimagesearch.app

import android.app.Application
import com.googleimagesearch.app.di.totalAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)

            modules(totalAppModules)
        }
    }
}
