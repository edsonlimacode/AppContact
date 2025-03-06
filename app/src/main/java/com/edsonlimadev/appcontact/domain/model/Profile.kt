package com.edsonlimadev.appcontact.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val name: String? = "",
    val email: String? = "",
    val image: String? = "",
): Parcelable
