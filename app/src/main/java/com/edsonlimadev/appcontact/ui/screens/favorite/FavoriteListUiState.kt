package com.edsonlimadev.appcontact.ui.screens.favorite

import com.edsonlimadev.appcontact.domain.model.Contact

data class FavoriteListUiState(
    val contactsFavorites: List<Contact>? = emptyList(),
    val error: String? = null
)