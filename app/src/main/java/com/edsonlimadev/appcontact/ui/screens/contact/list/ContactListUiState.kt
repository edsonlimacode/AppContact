package com.edsonlimadev.appcontact.ui.screens.contact.list

import com.edsonlimadev.appcontact.domain.model.Contact


data class ContactListUiState(
    val search: String = "",
    val contacts: List<Contact>? = emptyList(),
    val searchContacts: List<Contact>? = emptyList(),
    val error: String? = "",
    val onSearchChance: (String) -> Unit = {},
)