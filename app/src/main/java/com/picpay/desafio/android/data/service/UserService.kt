package com.picpay.desafio.android.data.service

import com.picpay.desafio.android.data.model.User
import retrofit2.Call
import retrofit2.http.GET


interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>
}