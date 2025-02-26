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
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val contactId = savedStateHandle.toRoute<ContactForm>()

    private val _uiState = MutableStateFlow(ContactFormUiState())
    val uiState: StateFlow<ContactFormUiState> = _uiState.asStateFlow()

    init {
        Log.i("contactForm", "id: $contactId")
    }


    fun getById(id: Long) = viewModelScope.launch {
        getByIdUseCase(id).fold(
            onSuccess = {
                _uiState.update { contactFormUiState ->
                    contactFormUiState.copy(contact = it)
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