package com.picpay.desafio.android.data.error.network

import com.picpay.desafio.android.data.error.base.Error

sealed class NetworkError : Error {
    object NotFound : NetworkError()
    object AccessDenied : NetworkError()
    object ServiceUnavailable : NetworkError()
    object Unknown : NetworkError()
}