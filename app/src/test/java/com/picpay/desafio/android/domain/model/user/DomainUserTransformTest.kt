package com.picpay.desafio.android.domain.model.user

import com.picpay.desafio.android.data.model.user.LocalUser
import com.picpay.desafio.android.data.model.user.RemoteUser
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class DomainUserTransformTest {
    private val transform = DomainUserTransform()
    private val domainUser = DomainUser(
        id = 1L,
        username = "username",
        name = "name",
        img = "img.com"
    )
    private val domainUsers = listOf(
        domainUser,
        domainUser.copy(id = 2L),
        domainUser.copy(id = 3L)
    )
    private val remoteUser = RemoteUser(
        id = 1L,
        username = "username",
        name = "name",
        img = "img.com"
    )
    private val remoteUsers = listOf(
        remoteUser,
        remoteUser.copy(id = 2L),
        remoteUser.copy(id = 3L)
    )
    private val localUser = LocalUser(
        id = 1L,
        username = "username",
        name = "name",
        img = "img.com"
    )
    private val localUsers = listOf(
        localUser,
        localUser.copy(id = 2L),
        localUser.copy(id = 3L)
    )

    @Test
    fun `Should convert data layer user to domain user`() {
        assertEquals(domainUser, transform.fromUserToDomainUser(localUser))
        assertEquals(domainUser, transform.fromUserToDomainUser(remoteUser))
    }

    @Test
    fun `Should convert data layer users to domain users`() {
        assertEquals(domainUsers, transform.fromUsersToDomainUsers(localUsers))
        assertEquals(domainUsers, transform.fromUsersToDomainUsers(remoteUsers))
    }

    @Test
    fun `Should convert domain user to data layer user`() {
        assertEquals(localUser, transform.fromDomainUserToLocalUser(domainUser))
        assertEquals(remoteUser, transform.fromDomainUserToRemoteUser(domainUser))
    }

    @Test
    fun `Should convert domain users to data layer users`() {
        assertEquals(localUsers, transform.fromDomainUsersToLocalUsers(domainUsers))
        assertEquals(remoteUsers, transform.fromDomainUsersToRemoteUsers(domainUsers))
    }
}