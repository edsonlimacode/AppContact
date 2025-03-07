package com.edsonlimadev.appcontact.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var id: Long = 0,
    var userId: String? = "",
    var name: String? = "",
    var number: String? = "",
    var address: String? = "",
    var addressNumber: String? = "",
    var neighborhood: String? = "",
    var city: String? = "",
    var uf: String? = "",
    var favorite: Boolean = false,
    var avatar: String? = "",
) : Parcelable