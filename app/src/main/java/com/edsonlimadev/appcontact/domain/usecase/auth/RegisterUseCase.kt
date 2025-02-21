package com.edsonlimadev.appcontact.domain.usecase.auth

import com.edsonlimadev.appcontact.data.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(name: String?, email: String, password: String): Result<Unit> {
        try {
            authRepository.register(name, email, password)
            return Result.success(Unit)
        } catch (ex: FirebaseAuthUserCollisionException) {
            return Result.failure(ex)
        } catch (ex: FirebaseAuthInvalidCredentialsException) {
            return Result.failure(ex)
        } catch (ex: FirebaseAuthWeakPasswordException) {
            return Result.failure(ex)
        }
    }
}