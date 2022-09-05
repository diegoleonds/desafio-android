package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.api.RetrofitConfig
import com.picpay.desafio.android.data.error.network.NetworkErrorHandler
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { UserRepositoryImpl(get(), get<NetworkErrorHandler>()) }
    single { RetrofitConfig.userService }
    factory { NetworkErrorHandler() }
}