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
): ViewModel() {

    private val _uiState = MutableStateFlow(ContactListUiState())
    val uiState: StateFlow<ContactListUiState> = _uiState.asStateFlow()

    init {
        getAll()
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
}