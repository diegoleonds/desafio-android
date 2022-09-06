package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.wrapper.Result
import com.picpay.desafio.android.domain.model.user.DomainUser
import com.picpay.desafio.android.domain.model.user.DomainUserTransform
import com.picpay.desafio.android.ui.extensions.getMessageResource
import com.picpay.desafio.android.ui.userlist.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchUsersUseCase(
    private val repository: UserRepository,
    private val transform: DomainUserTransform
) {
    suspend fun fetchUsers(): Flow<Result<List<DomainUser>>> {
        return flow {
            val result = repository.getUsersFromStore()
            result.collect {
                when (it) {
                    is Result.Success -> {
                        emit(Result.Success(transform.fromUsersToDomainUsers(it.data)))
                    }
                    is Result.Fail -> {
                        emit(it as Result.Fail<List<DomainUser>>)
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}