package com.picpay.desafio.android.data.model.user

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class UserTransformTest {
    private val transform = UserTransform()
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
    fun `Should transform localUser to a remoteUser with same data`() {
        assertEquals(remoteUser, transform.toRemoteUser(localUser))
    }

    @Test
    fun `Should transform localUsers to remoteUsers with same data`() {
        assertEquals(remoteUsers, transform.toRemoteUsers(localUsers))
    }

    @Test
    fun `Should transform remoteUser to a localUser with same data`() {
        assertEquals(localUser, transform.toLocalUser(remoteUser))
    }

    @Test
    fun `Should transform remoteUsers to localUsers with same data`() {
        assertEquals(localUsers, transform.toLocalUsers(remoteUsers))
    }
}