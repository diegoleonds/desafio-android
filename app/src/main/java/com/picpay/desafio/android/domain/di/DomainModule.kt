package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.model.user.DomainUserTransform
import com.picpay.desafio.android.domain.usecase.FetchUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchUsersUseCase(get<UserRepositoryImpl>(), get()) }
    factory { DomainUserTransform() }
}