package com.picpay.desafio.android.data.repository

import com.dropbox.android.external.store4.*
import com.picpay.desafio.android.data.database.dao.UserDao
import com.picpay.desafio.android.data.error.base.ErrorHandler
import com.picpay.desafio.android.data.error.network.NetworkError
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.data.model.user.UserTransform
import com.picpay.desafio.android.data.service.UserService
import com.picpay.desafio.android.data.wrapper.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface UserRepository {
    suspend fun getUsersFromStore(): Flow<Result<List<User>>>
}

class UserRepositoryImpl(
    private val remoteDataSource: UserService,
    private val localDataSource: UserDao,
    private val errorHandler: ErrorHandler,
    private val transform: UserTransform
) : UserRepository {

    companion object {
        private const val getUsers = "get_users"
    }

    override suspend fun getUsersFromStore(): Flow<Result<List<User>>> {
        val store = StoreBuilder
            .from(
                fetcher = Fetcher.of { remoteDataSource.getUsers() },
                sourceOfTruth = SourceOfTruth.of(
                    reader = { localDataSource.getAll() },
                    writer = { _, users -> localDataSource.insertAll(transform.toLocalUsers(users)) }
                )
            ).build()

        return flow {
            store.stream(StoreRequest.cached(key = getUsers, refresh = true))
                .flowOn(Dispatchers.IO)
                .collect { response: StoreResponse<List<User>> ->
                    when (response) {
                        is StoreResponse.Error -> {
                            if (response is StoreResponse.Error.Exception) {
                                emit(Result.Fail(errorHandler.getError(response.error)))
                            } else {
                                emit(Result.Fail(NetworkError.Unknown))
                            }
                        }
                        is StoreResponse.Data -> {
                            if (response.value.isNotEmpty()) {
                                emit(Result.Success(response.value))
                            }
                        }
                        else -> {}
                    }
                }
        }
    }
}