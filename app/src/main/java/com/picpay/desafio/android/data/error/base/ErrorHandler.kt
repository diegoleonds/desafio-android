package com.picpay.desafio.android.data.error.base

interface ErrorHandler {
    fun getError(throwable: Throwable): Error
}