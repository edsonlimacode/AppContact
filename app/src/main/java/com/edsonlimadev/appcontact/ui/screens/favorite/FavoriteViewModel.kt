package com.edsonlimadev.appcontact.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.domain.usecase.contact.GetAllFavoritesUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteListUiState())
    val uiState: StateFlow<FavoriteListUiState> = _uiState.asStateFlow()

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() = viewModelScope.launch {
        getAllFavoritesUseCase().fold(
            onSuccess = {
                it.collect { contactsList ->
                    contactsList?.let {
                        _uiState.update { contactFormUiState ->
                            contactFormUiState.copy(contactsFavorites = it)
                        }
                    }
                }
            },
            onFailure = {
                _uiState.update { contactFormUiState ->
                    contactFormUiState.copy(error = it.message)
                }
            }
        )
    }

    fun removeFavorite(contact: Contact) = viewModelScope.launch {

        removeFavoriteUseCase(contact).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update { contactDetailsUiState ->
                    contactDetailsUiState.copy(error = it.message)
                }
            }
        )
    }

}