package com.edsonlimadev.appcontact.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.domain.usecase.contact.AddToFavoriteUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.GetByIdUseCase
import com.edsonlimadev.appcontact.ui.navigation.ContactForm
import com.edsonlimadev.appcontact.ui.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getByIdUseCase: GetByIdUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactDetailsUiState())
    val uiState: StateFlow<ContactDetailsUiState> = _uiState.asStateFlow()

    private val contactId = savedStateHandle.toRoute<Detail>()

    init {
        getById(contactId.id)
    }

    private fun getById(id: Long) = viewModelScope.launch {
        getByIdUseCase(id).fold(
            onSuccess = {
                _uiState.update { contactDetailsUiState ->
                    contactDetailsUiState.copy(contact = it)
                }
            },
            onFailure = {
                _uiState.update { contactDetailsUiState ->
                    contactDetailsUiState.copy(error = it.message)
                }
            }
        )
    }

    fun addToFavorite(contact: Contact) = viewModelScope.launch {

        addToFavoriteUseCase(contact).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update { contactDetailsUiState ->
                    contactDetailsUiState.copy(error = it.message)
                }
            }
        )
    }

}