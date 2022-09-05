package com.picpay.desafio.android.data.wrapper

import com.picpay.desafio.android.data.error.base.Error

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Fail<T>(val error: Error) : Result<T>()
}