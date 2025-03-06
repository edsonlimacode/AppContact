package com.edsonlimadev.appcontact.di

import com.edsonlimadev.appcontact.data.repositories.AuthRepository
import com.edsonlimadev.appcontact.data.repositories.ContactRepository
import com.edsonlimadev.appcontact.data.repositories.ProfileRepository
import com.edsonlimadev.appcontact.domain.repositories.IAuthRepository
import com.edsonlimadev.appcontact.domain.repositories.IContactRepository
import com.edsonlimadev.appcontact.domain.repositories.IProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthRepository
    ): IAuthRepository

    @Binds
    abstract fun provideContatoRepository(
        contactRepository: ContactRepository
    ): IContactRepository

    @Binds
    abstract fun provideProfile(
        profileRepository: ProfileRepository
    ): IProfileRepository
}