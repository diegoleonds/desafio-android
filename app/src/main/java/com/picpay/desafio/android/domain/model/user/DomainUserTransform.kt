package com.picpay.desafio.android.domain.model.user

import com.picpay.desafio.android.data.model.user.LocalUser
import com.picpay.desafio.android.data.model.user.RemoteUser

class DomainUserTransform {
    fun fromUserToDomainUser(user: User): DomainUser {
        return DomainUser(
            id = user.id,
            username = user.username,
            name = user.name,
            img = user.img
        )
    }

    fun fromUsersToDomainUsers(users: List<User>): List<DomainUser> {
        return convertList(users) { fromUserToDomainUser(it) }
    }

    fun fromDomainUserToLocalUser(domainUser: DomainUser): LocalUser {
        return LocalUser(
            id = domainUser.id,
            username = domainUser.username,
            name = domainUser.name,
            img = domainUser.img
        )
    }

    fun fromDomainUsersToLocalUsers(domainUsers: List<DomainUser>): List<LocalUser> {
        return convertList(domainUsers) { fromDomainUserToLocalUser(it) }
    }

    fun fromDomainUserToRemoteUser(domainUser: DomainUser): RemoteUser {
        return RemoteUser(
            id = domainUser.id,
            username = domainUser.username,
            name = domainUser.name,
            img = domainUser.img
        )
    }

    fun fromDomainUsersToRemoteUsers(domainUsers: List<DomainUser>): List<RemoteUser> {
        return convertList(domainUsers) { fromDomainUserToRemoteUser(it) }
    }

    private fun <I: User, O: User> convertList(users: List<I>, convertBlock: (user: I) -> O): List<O> {
        val outputUsers = ArrayList<O>()
        users.forEach {
            outputUsers.add(convertBlock(it))
        }
        return outputUsers
    }
}