package com.edsonlimadev.appcontact.di

import com.edsonlimadev.appcontact.data.repositories.AuthRepository
import com.edsonlimadev.appcontact.domain.repositories.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepository
    ): IAuthRepository

}