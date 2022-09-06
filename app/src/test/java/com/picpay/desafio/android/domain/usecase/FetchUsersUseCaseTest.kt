package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.model.user.LocalUser
import com.picpay.desafio.android.data.model.user.RemoteUser
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.wrapper.Result
import com.picpay.desafio.android.domain.model.user.DomainUser
import com.picpay.desafio.android.domain.model.user.DomainUserTransform
import com.picpay.desafio.android.domain.model.user.User
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

internal class FetchUsersUseCaseTest {
    private val repository: UserRepository = mockk()
    private val transform: DomainUserTransform = spyk()
    private val useCase = FetchUsersUseCase(
        repository,
        transform
    )

    private val user = DomainUser(
        id = 1L,
        username = "username",
        name = "name",
        img = "img.com"
    )
    private val users = listOf(
        user,
        user.copy(id = 2L),
        user.copy(id = 3L)
    )
    private val remoteUser = RemoteUser(
        id = 1L,
        username = "username",
        name = "name",
        img = "img.com"
    )
    private val remoteUsers = listOf(
        remoteUser,
        remoteUser.copy(id = 2L),
        remoteUser.copy(id = 3L)
    )
    private val localUser = LocalUser(
        id = 1L,
        username = "username",
        name = "name",
        img = "img.com"
    )
    private val localUsers = listOf(
        localUser,
        localUser.copy(id = 2L),
        localUser.copy(id = 3L)
    )

    @Test
    fun `Should emit fail when repository emits it`() = runBlocking {
        val flow = flow<Result<List<User>>> {
            emit(Result.Fail(mockk()))
        }
        coEvery { repository.getUsers() } returns flow

        val result = useCase.fetchUsers().take(1).toList()[0]
        assertTrue(result is Result.Fail)
    }

    @Test
    fun `Should emit success when repository emits it`() = runBlocking {
        val flow = flow<Result<List<User>>> {
            emit(Result.Success(remoteUsers))
        }
        coEvery { repository.getUsers() } returns flow

        val result = useCase.fetchUsers().take(1).toList()[0]
        assertTrue(result is Result.Success)
    }
}