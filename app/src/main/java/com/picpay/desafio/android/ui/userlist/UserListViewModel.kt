package com.picpay.desafio.android.ui.userlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.data.wrapper.Result
import com.picpay.desafio.android.domain.usecase.FetchUsersUseCase
import com.picpay.desafio.android.ui.extensions.getMessageResource
import kotlinx.coroutines.launch

sealed class UiState {
    data class Success(val users: List<User>) : UiState()
    data class Error(val messageResource: Int) : UiState()
    object Loading : UiState()
}

class UserListViewModel(
    private val useCase: FetchUsersUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf<UiState>(UiState.Loading)
    val uiState: State<UiState>
        get() = _uiState

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            useCase.fetchUsers().collect { result ->
                when (result) {
                    is Result.Success -> _uiState.value = UiState.Success(result.data)
                    is Result.Fail -> {
                        if (uiState.value !is UiState.Success) {
                            _uiState.value = UiState.Error(result.error.getMessageResource())
                        }
                    }
                }
            }
        }
    }
}