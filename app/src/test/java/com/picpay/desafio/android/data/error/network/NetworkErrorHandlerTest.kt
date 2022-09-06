package com.picpay.desafio.android.data.error.network

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import retrofit2.HttpException
import java.net.HttpURLConnection

internal class NetworkErrorHandlerTest {
    private val errorHandler = NetworkErrorHandler()
    private val mockkHttpException = mockk<HttpException>()

    @Test
    fun `Should return NotFound when receive the same error`() {
        val expectedError = NetworkError.NotFound
        every { mockkHttpException.code() } returns HttpURLConnection.HTTP_NOT_FOUND

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun `Should not return NotFound when receive a different error`() {
        val expectedError = NetworkError.NotFound
        every { mockkHttpException.code() } returns -1

        Assert.assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun `Should return Forbidden when receive the same error`() {
        val expectedError = NetworkError.AccessDenied
        every { mockkHttpException.code() } returns HttpURLConnection.HTTP_FORBIDDEN

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun `Should not return Forbidden when receive a different error`() {
        val expectedError = NetworkError.AccessDenied
        every { mockkHttpException.code() } returns -1

        Assert.assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun `Should return Unavailable when receive the same error`() {
        val expectedError = NetworkError.ServiceUnavailable
        every { mockkHttpException.code() } returns HttpURLConnection.HTTP_UNAVAILABLE

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun `Should not return Unavailable when receive a different error`() {
        val expectedError = NetworkError.ServiceUnavailable
        every { mockkHttpException.code() } returns -1

        Assert.assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun `Should return Unknown when error is not defined`() {
        val expectedError = NetworkError.Unknown
        every { mockkHttpException.code() } returns -1

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }
}