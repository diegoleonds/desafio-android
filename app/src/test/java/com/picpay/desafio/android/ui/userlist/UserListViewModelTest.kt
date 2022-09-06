package com.picpay.desafio.android.ui.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.wrapper.Result
import com.picpay.desafio.android.domain.model.user.DomainUser
import com.picpay.desafio.android.domain.usecase.FetchUsersUseCase
import com.picpay.desafio.android.util.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class UserListViewModelTest {
    private val useCase: FetchUsersUseCase = mockk()
    private lateinit var viewModel: UserListViewModel

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

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineRule()

    @Test
    fun `Should set uiState as Success when useCase fetch is successful`() =
        runBlocking {
            val flow = flow<Result<List<DomainUser>>> {
                emit(Result.Success(users))
            }
            coEvery { useCase.fetchUsers() } returns flow
            initViewModel()

            assertTrue(viewModel.uiState.value is UiState.Success)
            assertEquals(users, (viewModel.uiState.value as UiState.Success).users)
        }

    @Test
    fun `Should set uiState as Fail when useCase fetch fails and uiState not have a previous success value`() =
        runBlocking {
            val flow = flow<Result<List<DomainUser>>> {
                emit(Result.Fail(mockk()))
            }
            coEvery { useCase.fetchUsers() } returns flow
            initViewModel()

            assertTrue(viewModel.uiState.value is UiState.Error)
        }

    @Test
    fun `Should not set uiState as Fail when useCase fetch fails and uiState have a previous success value`() =
        runBlocking {
            val flow = flow {
                emit(Result.Success(users))
                emit(Result.Fail(mockk()))
            }
            coEvery { useCase.fetchUsers() } returns flow
            initViewModel()

            assertFalse(viewModel.uiState.value is UiState.Error)
            assertTrue(viewModel.uiState.value is UiState.Success)
            assertEquals(users, (viewModel.uiState.value as UiState.Success).users)
        }

    private fun initViewModel() {
        viewModel = UserListViewModel(useCase)
    }
}