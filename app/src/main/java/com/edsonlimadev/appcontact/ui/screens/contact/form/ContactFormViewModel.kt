package com.edsonlimadev.appcontact.ui.screens.contact.form

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.edsonlimadev.appcontact.domain.mapper.toEntity
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.domain.usecase.contact.AddToFavoriteUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.DeleteUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.GetAllUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.GetByIdUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.InsertUseCase
import com.edsonlimadev.appcontact.domain.usecase.contact.UpdateUseCase
import com.edsonlimadev.appcontact.ui.navigation.ContactForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.contracts.contract

@HiltViewModel
class ContactFormViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val updateUseCase: UpdateUseCase,
    private val getByIdUseCase: GetByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val contact = savedStateHandle.toRoute<ContactForm>()

    private val _uiState = MutableStateFlow(ContactFormUiState())
    val uiState: StateFlow<ContactFormUiState> = _uiState.asStateFlow()

    init {
        setFormData()
        contact.id?.let {
            getById(it)
        }
    }

    private fun setFormData() {
        _uiState.update { contactFormUiState ->
            contactFormUiState.copy(
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onNumberChange = {
                    _uiState.value = _uiState.value.copy(
                        number = it
                    )
                },
                onAddressChange = {
                    _uiState.value = _uiState.value.copy(
                        address = it
                    )
                },
                onAddressNumberChange = {
                    _uiState.value = _uiState.value.copy(
                        addressNumber = it
                    )
                },
                onNeighborhoodChange = {
                    _uiState.value = _uiState.value.copy(
                        neighborhood = it
                    )
                },
                onCityChange = {
                    _uiState.value = _uiState.value.copy(
                        city = it
                    )
                },
                onUfChange = {
                    _uiState.value = _uiState.value.copy(
                        uf = it
                    )
                }
            )
        }
    }

    private fun getById(id: Long) = viewModelScope.launch {
        getByIdUseCase(id).fold(
            onSuccess = { contact ->
                _uiState.update { contactFormUiState ->
                    contactFormUiState.copy(
                        id = contact.id,
                        name = contact.name ?: "",
                        number = contact.number ?: "",
                        address = contact.address ?: "",
                        addressNumber = contact.addressNumber ?: "",
                        neighborhood = contact.neighborhood ?: "",
                        city = contact.city ?: "",
                        uf = contact.uf ?: ""
                    )
                }
            },
            onFailure = {
                _uiState.update { contactFormUiState ->
                    contactFormUiState.copy(error = it.message)
                }
            }
        )
    }

    fun insert(contact: Contact) = viewModelScope.launch {
        insertUseCase(contact).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update { contactFormUiState ->
                    contactFormUiState.copy(error = it.message)
                }
            }
        )
    }

    fun update(contact: Contact) = viewModelScope.launch {
        updateUseCase(contact).fold(
            onSuccess = {},
            onFailure = {
                _uiState.update { contactFormUiState ->
                    contactFormUiState.copy(error = it.message)
                }
            }
        )
    }
}