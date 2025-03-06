package com.edsonlimadev.appcontact.ui.screens.profile

import com.edsonlimadev.appcontact.domain.model.Profile

sealed class ProfileUiState {
    data class Success(val profile: Profile) : ProfileUiState()
    data class Error(val message: String? = "") : ProfileUiState()
    object Loading : ProfileUiState()
}
