package com.picpay.desafio.android.ui.extensions

import com.picpay.desafio.android.data.error.base.Error
import com.picpay.desafio.android.data.error.network.NetworkError
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import com.picpay.desafio.android.R

internal class ErrorExtensionsKtTest {
    @Test
    fun `Should return NetworkError NotFound resource`() {
        assertEquals(R.string.not_found_error, NetworkError.NotFound.getMessageResource())
    }

    @Test
    fun `Should return AccessDenied NotFound resource`() {
        assertEquals(R.string.access_denied_error, NetworkError.AccessDenied.getMessageResource())
    }

    @Test
    fun `Should return NetworkError ServiceUnavailable resource`() {
        assertEquals(R.string.service_unavailable_error, NetworkError.ServiceUnavailable.getMessageResource())
    }

    @Test
    fun `Should return Unknown error resource`() {
        val testError = object : Error {}
        assertEquals(R.string.unknown_error, testError.getMessageResource())
    }
}