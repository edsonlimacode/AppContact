package com.edsonlimadev.appcontact.domain.repositories

import android.net.Uri
import com.edsonlimadev.appcontact.domain.model.Profile

interface IProfileRepository {

    suspend fun saveProfile(profile: Profile)
    suspend fun getProfileById(id: String): Profile
    suspend fun saveImageToStorage(uri: Uri): String

}