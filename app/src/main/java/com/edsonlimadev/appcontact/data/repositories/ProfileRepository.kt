package com.edsonlimadev.appcontact.data.repositories

import android.net.Uri
import com.edsonlimadev.appcontact.domain.model.Profile
import com.edsonlimadev.appcontact.domain.repositories.IProfileRepository
import com.edsonlimadev.appcontact.utils.getFirestore
import com.edsonlimadev.appcontact.utils.getStorage
import com.edsonlimadev.appcontact.utils.getUserId
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ProfileRepository @Inject constructor() : IProfileRepository {

    override suspend fun saveProfile(profile: Profile) {
        getFirestore().collection("profile")
            .document(getUserId().toString())
            .set(profile)
            .await()
    }

    override suspend fun getProfileById(id: String): Profile {
        return suspendCoroutine { continuation ->
            getFirestore().collection("profile")
                .document(id)
                .get()
                .addOnCompleteListener { documentSnapshot ->

                    val profile = documentSnapshot.result.toObject(Profile::class.java)
                    profile?.let {
                        continuation.resume(it)
                    }

                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }

    }

    override suspend fun saveImageToStorage(uri: Uri): String {
        return suspendCoroutine { continuation ->
            getStorage().getReference("profile")
                .child("${getUserId()}.jpg")
                .putFile(uri)
                .addOnSuccessListener { uploadResult ->

                    uploadResult.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri ->

                        getFirestore().collection("profile")
                            .document(getUserId().toString())
                            .update("image", uri.toString())
                            .addOnSuccessListener {
                                continuation.resume(uri.toString())
                            }.addOnFailureListener {
                                continuation.resumeWithException(it)
                            }
                    }
                }
        }

    }
}