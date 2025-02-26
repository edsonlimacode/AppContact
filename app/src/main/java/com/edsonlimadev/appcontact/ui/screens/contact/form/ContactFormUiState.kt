package com.edsonlimadev.appcontact.ui.screens.contact.form

import com.edsonlimadev.appcontact.domain.model.Contact


/*
data class ContactFormUiState(
    val contact: Contact? = null,
    val loading: Boolean = true,
    val error: String? = null
)*/

data class ContactFormUiState(
    val id: Long = 0,
    val name: String = "",
    val number: String = "",
    val address: String = "",
    val addressNumber: String = "",
    val neighborhood: String = "",
    val city: String = "",
    val uf: String = "",
    val onNameChange: (String) -> Unit = {},
    val onNumberChange: (String) -> Unit = {},
    val onAddressChange: (String) -> Unit = {},
    val onAddressNumberChange: (String) -> Unit = {},
    val onNeighborhoodChange: (String) -> Unit = {},
    val onCityChange: (String) -> Unit = {},
    val onUfChange: (String) -> Unit = {},
    val error: String? = null
)