package com.edsonlimadev.appcontact.domain.model

data class Contact(
    var id: Long = 0,
    var name: String? = "",
    var number: String? = "",
    var address: String? = "",
    var addressNumber: String? = "",
    var neighborhood: String? = "",
    var city: String? = "",
    var uf: String? = "",
    var favorite: Boolean? = false
)