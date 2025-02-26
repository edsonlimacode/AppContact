package com.edsonlimadev.appcontact.ui.screens.contact.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.appcontact.domain.usecase.contact.GetAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val getAllUseCase: GetAllUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactListUiState())
    val uiState: StateFlow<ContactListUiState> = _uiState.asStateFlow()

    init {
        getAll()
        _uiState.update { contactFormUiState ->
            contactFormUiState.copy(onSearchChance = {
                _uiState.value = _uiState.value.copy(search = it)
                searchContacts(it)
            })
        }
    }

    private fun getAll() = viewModelScope.launch {
        getAllUseCase().fold(
            onSuccess = {
                it.collect { contactsList ->
                    contactsList?.let {
                        _uiState.update { contactFormUiState ->
                            contactFormUiState.copy(contacts = it)
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

    private fun searchContacts(search: String) = viewModelScope.launch {
        getAllUseCase().fold(
            onSuccess = {
                it.collect { contactsList ->
                    contactsList?.let { contactList ->
                        _uiState.update { contactFormUiState ->
                            val filterContacts =
                                contactList.filter { contact ->
                                    contact.name?.contains(_uiState.value.search, true) == true
                                }

                            contactFormUiState.copy(searchContacts = filterContacts)
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
}