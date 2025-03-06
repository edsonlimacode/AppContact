package com.edsonlimadev.appcontact.domain.usecase.profile

import android.util.Log
import com.edsonlimadev.appcontact.data.repositories.ProfileRepository
import com.edsonlimadev.appcontact.domain.model.Profile
import javax.inject.Inject

class GetProfileByUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(id: String): Result<Profile> {
        try {
            val profile = profileRepository.getProfileById(id)
            return Result.success(profile)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}