package com.edsonlimadev.appcontact.domain.model

data class Contact(
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val address: String = "",
    val addressNumber: Int = 0,
    val neighborhood: String = "",
    val city: String = "",
    val uf: String = "",
)