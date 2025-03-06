package com.edsonlimadev.appcontact.domain.usecase.profile

import com.edsonlimadev.appcontact.data.repositories.ProfileRepository
import com.edsonlimadev.appcontact.domain.model.Profile
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(profile: Profile): Result<Unit> {
        try {
            profileRepository.saveProfile(profile)
            return Result.success(Unit)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}