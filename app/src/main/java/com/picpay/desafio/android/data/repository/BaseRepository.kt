package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.error.base.ErrorHandler
import com.picpay.desafio.android.data.wrapper.Result

abstract class BaseRepository(
    private val errorHandler: ErrorHandler
) {
    protected open suspend fun <T> wrapInResult(block: suspend () -> T): Result<T> {
        return try {
            Result.Success(block())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Fail(errorHandler.getError(e))
        }
    }
}