package com.picpay.desafio.android.ui.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.wrapper.Result
import com.picpay.desafio.android.util.MainCoroutineRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

internal class UserListViewModelTest {
    private val repository: UserRepository = mockk()
    private lateinit var viewModel: UserListViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineRule()

    @BeforeEach
    fun initViewModel() {
        viewModel = UserListViewModel(repository)
    }

    @Test
    fun `Should set uiState as Success when repository fetch is successful and not return an empty list`() =
        runBlocking {
            val flow = flow {
                emit(2)
                delay(10)
                emit(1)
            }
        }

    @Test
    fun `Should not set uiState as Success when repository fetch is successful and not return an empty list`() =
        runBlocking {

        }

    @Test
    fun `Should set uiState as Fail when repository fetch fails and uiState not have a previous success value`() =
        runBlocking {

        }

    @Test
    fun `Should not set uiState as Fail when repository fetch fails and uiState have a previous success value`() =
        runBlocking {

        }
}