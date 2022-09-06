package com.picpay.desafio.android.data.model.user

import com.picpay.desafio.android.domain.model.user.User

class UserTransform {
    fun toLocalUser(remoteUser: RemoteUser): LocalUser {
        return LocalUser(
            id = remoteUser.id,
            name = remoteUser.name,
            username = remoteUser.username,
            img = remoteUser.img
        )
    }

    fun toLocalUsers(remoteUsers: List<RemoteUser>): List<LocalUser> {
        return convertList(remoteUsers) { toLocalUser(it) }
    }

    fun toRemoteUser(localUser: LocalUser): RemoteUser {
        return RemoteUser(
            id = localUser.id,
            name = localUser.name,
            username = localUser.username,
            img = localUser.img
        )
    }

    fun toRemoteUsers(localUsers: List<LocalUser>): List<RemoteUser> {
        return convertList(localUsers) { toRemoteUser(it) }
    }

    private fun <I: User, O: User> convertList(users: List<I>, convertBlock: (user: I) -> O): List<O> {
        val outputUsers = ArrayList<O>()
        users.forEach {
            outputUsers.add(convertBlock(it))
        }
        return outputUsers
    }
}