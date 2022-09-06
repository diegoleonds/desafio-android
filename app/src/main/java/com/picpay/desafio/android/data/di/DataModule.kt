package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.api.RetrofitConfig
import com.picpay.desafio.android.data.database.AppDatabase
import com.picpay.desafio.android.data.error.network.NetworkErrorHandler
import com.picpay.desafio.android.data.model.user.UserTransform
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { UserRepositoryImpl(get(), get(), get<NetworkErrorHandler>(), UserTransform()) }
    single { RetrofitConfig.userService }
    factory { NetworkErrorHandler() }

    single { AppDatabase.create(androidApplication()) }
    single { provideUserDao(get()) }
}

private fun provideUserDao(dataBase: AppDatabase) = dataBase.userDao()