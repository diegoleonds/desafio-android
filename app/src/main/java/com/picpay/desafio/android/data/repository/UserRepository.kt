package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.error.base.ErrorHandler
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.UserService
import com.picpay.desafio.android.data.wrapper.Result

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}

class UserRepositoryImpl(
    private val dataSource: UserService,
    errorHandler: ErrorHandler
): UserRepository, BaseRepository(errorHandler) {

    override suspend fun getUsers(): Result<List<User>> {
        return wrapInResult { dataSource.getUsers() }
    }
}