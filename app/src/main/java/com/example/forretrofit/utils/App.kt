package com.example.forretrofit.utils

import android.app.Application
import com.example.forretrofit.di.appModule
import com.example.forretrofit.di.dataModule
import com.example.forretrofit.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val context = this
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            modules(listOf(appModule, dataModule, networkModule))
            androidContext(this@App)
        }
    }
}