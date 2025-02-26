package com.edsonlimadev.appcontact.ui.screens.details

import com.edsonlimadev.appcontact.domain.model.Contact


data class ContactDetailsUiState(
    val contact: Contact? = null,
    val loading: Boolean = false,
    val error: String? = ""
)