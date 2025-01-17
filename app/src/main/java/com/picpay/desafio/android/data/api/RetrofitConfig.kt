package com.picpay.desafio.android.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.service.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private const val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}