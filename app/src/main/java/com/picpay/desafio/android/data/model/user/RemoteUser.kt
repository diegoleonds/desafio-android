package com.picpay.desafio.android.data.model.user

import com.picpay.desafio.android.domain.model.user.User
import java.io.Serializable

data class RemoteUser(
    override val img: String,
    override val name: String,
    override val id: Long,
    override val username: String
) : User, Serializable