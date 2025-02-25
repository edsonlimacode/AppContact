package com.edsonlimadev.appcontact.ui.screens.contact.form

import com.edsonlimadev.appcontact.domain.model.Contact


data class ContactFormUiState(
    val contacts: List<Contact>? = emptyList(),
    val contact: Contact? = null,
    val loading: Boolean = false,
    val error: String? = null
)