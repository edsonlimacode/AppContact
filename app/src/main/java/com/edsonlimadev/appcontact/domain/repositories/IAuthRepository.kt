package com.edsonlimadev.appcontact.domain.repositories

interface IAuthRepository {

    suspend fun login(email: String, password: String)
    suspend fun register(name: String?, email: String, password: String)
    suspend fun loginWithGoogle()

}