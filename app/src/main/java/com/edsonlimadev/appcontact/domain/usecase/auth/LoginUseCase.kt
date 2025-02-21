package com.edsonlimadev.appcontact.domain.usecase.auth

import com.edsonlimadev.appcontact.data.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        try {
            authRepository.login(email, password)
            return Result.success(Unit)
        } catch (ex: FirebaseAuthInvalidCredentialsException) {
            return Result.failure(ex)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}