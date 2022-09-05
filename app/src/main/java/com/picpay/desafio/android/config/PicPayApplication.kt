package com.picpay.desafio.android.config

import android.app.Application
import com.picpay.desafio.android.data.di.dataModule
import com.picpay.desafio.android.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicPayApplication)
            modules(uiModule, dataModule)
        }
    }
}