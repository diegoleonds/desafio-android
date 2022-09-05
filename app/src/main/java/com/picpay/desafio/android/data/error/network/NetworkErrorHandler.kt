package com.picpay.desafio.android.data.error.network

import com.picpay.desafio.android.data.error.base.ErrorHandler
import retrofit2.HttpException
import java.net.HttpURLConnection

class NetworkErrorHandler : ErrorHandler {
    override fun getError(throwable: Throwable): NetworkError {
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> NetworkError.NotFound
                    HttpURLConnection.HTTP_FORBIDDEN -> NetworkError.AccessDenied
                    HttpURLConnection.HTTP_UNAVAILABLE -> NetworkError.ServiceUnavailable
                    else -> NetworkError.Unknown
                }
            }
            else -> NetworkError.Unknown
        }
    }
}