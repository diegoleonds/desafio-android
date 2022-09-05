package com.picpay.desafio.android.ui.extensions

import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.error.base.Error
import com.picpay.desafio.android.data.error.network.NetworkError

fun Error.getMessageResource(): Int {
    return when (this) {
        is NetworkError.NotFound -> R.string.not_found_error
        is NetworkError.AccessDenied -> R.string.access_denied_error
        is NetworkError.ServiceUnavailable -> R.string.service_unavailable_error
        else -> R.string.unknown_error
    }
}