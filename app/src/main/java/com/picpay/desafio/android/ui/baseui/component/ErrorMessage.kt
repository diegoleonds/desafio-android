package com.picpay.desafio.android.ui.baseui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.baseui.theme.PicPayTheme

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    BaseErrorMessage(
        message,
        modifier
    )
}

@Composable
fun TryAgainErrorMessage(
    errorMessage: String,
    modifier: Modifier = Modifier,
    tryAgainMessage: String = stringResource(R.string.try_again),
    action: () -> Unit
) {
    BaseErrorMessage(
        errorMessage,
        modifier
    ) {
        Button(
            onClick = { action() },
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            Text(tryAgainMessage)
        }
    }
}

@Composable
private fun BaseErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    additionalContent:  @Composable ColumnScope.() -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(all = 12.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        additionalContent()
    }
}

@Preview("ErrorMessage")
@Composable
private fun PreviewErrorMessage() {
    PicPayTheme {
        ErrorMessage(
            "Erro de rede, verifique sua conexão com a internet"
        )
    }
}

@Preview("TryAgainErrorMessage")
@Composable
private fun PreviewTryAgainErrorMessage() {
    PicPayTheme {
        TryAgainErrorMessage(
            "Erro de rede, verifique sua conexão com a internet"
        ) {

        }
    }
}

@Preview("BaseErrorMessage")
@Composable
private fun PreviewBaseErrorMessage() {
    PicPayTheme {
        BaseErrorMessage(
            "Erro de rede, verifique sua conexão com a internet"
        )
    }
}