package com.edsonlimadev.appcontact.domain.usecase.profile

import android.net.Uri
import com.edsonlimadev.appcontact.data.repositories.ProfileRepository
import javax.inject.Inject

class SaveImageProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(uri: Uri): Result<String> {
        try {
          val uriResult =  profileRepository.saveImageToStorage(uri)
            return Result.success(uriResult)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}