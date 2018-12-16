package com.noox.postschallenge

import android.app.Application
import com.noox.postschallenge.common.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(context = this, modules = listOf(appModule))
    }
}