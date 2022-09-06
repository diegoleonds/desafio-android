package com.picpay.desafio.android.domain.model.user

data class DomainUser(
    override val id: Long,
    override val img: String,
    override val name: String,
    override val username: String
): User