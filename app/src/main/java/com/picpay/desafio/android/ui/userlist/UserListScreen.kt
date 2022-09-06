package com.picpay.desafio.android.ui.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.domain.model.user.DomainUser
import com.picpay.desafio.android.ui.baseui.component.AsyncUserItem
import com.picpay.desafio.android.ui.baseui.component.LoadingIndicator
import com.picpay.desafio.android.ui.baseui.component.TryAgainErrorMessage
import com.picpay.desafio.android.ui.baseui.theme.PicPayTheme

@Composable
fun UserListScreen(viewModel: UserListViewModel) {
    val uiState = viewModel.uiState

    when (val state = uiState.value) {
        is UiState.Success -> SuccessStateScreen(users = state.users)
        is UiState.Loading -> LoadingStateScreen()
        is UiState.Error -> {
            ErrorStateScreen(
                errorMessage = stringResource(state.messageResource)
            ) {
                viewModel.fetchUsers()
            }
        }
    }
}

@Composable
private fun SuccessStateScreen(
    users: List<User>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        item {
            Text(
                text = stringResource(R.string.title),
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        top = 48.dp,
                        bottom = 24.dp
                    )
            )
        }
        items(users) { user ->
            AsyncUserItem(
                name = user.name,
                username = user.username,
                imageUrl = user.img
            )
        }
    }
}

@Composable
private fun LoadingStateScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LoadingIndicator()
    }
}

@Composable
private fun ErrorStateScreen(
    errorMessage: String,
    tryAgainAction: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 12.dp)
    ) {
        TryAgainErrorMessage(
            errorMessage
        ) {
            tryAgainAction()
        }
    }
}

@Composable
@Preview("Success State")
private fun PreviewSuccessState() {
    val users = ArrayList<DomainUser>()
    for (i in 0 until 5) {
        users.add(
            DomainUser(
                id = i.toLong(),
                name = "Test $i",
                username = "Test username $i",
                img = "no"
            )
        )
    }
    PicPayTheme {
        SuccessStateScreen(users)
    }
}

@Composable
@Preview("Error State")
private fun PreviewErrorState() {
    PicPayTheme {
        ErrorStateScreen(errorMessage = "Erro ao conectar. " +
                "Verifique sua conexão com a Internet")
    }
}

@Composable
@Preview("Loading State")
private fun PreviewLoadingState() {
    PicPayTheme {
        LoadingStateScreen()
    }
}