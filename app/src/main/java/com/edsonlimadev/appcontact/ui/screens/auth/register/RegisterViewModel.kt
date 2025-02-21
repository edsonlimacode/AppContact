package com.edsonlimadev.appcontact.ui.screens.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.appcontact.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _toastMessage = MutableStateFlow(RegisterErrorUiEvent())
    val toastMessage: StateFlow<RegisterErrorUiEvent> = _toastMessage.asStateFlow()

    init {
        setFormValues()
    }

    fun register() = viewModelScope.launch {

        if (_uiState.value.email.isNotEmpty()) {
            if (_uiState.value.password.isNotEmpty()) {
                registerUseCase(
                    uiState.value.name,
                    uiState.value.email,
                    uiState.value.password
                ).fold(
                    onSuccess = {
                        viewModelScope.launch {
                            _toastMessage.value =
                                RegisterErrorUiEvent.Success("Cadastro realizado com sucesso")
                            clearToastMessage()
                        }
                    },
                    onFailure = { exception ->
                        viewModelScope.launch {
                            val message = when {
                                exception.message?.contains("The email address is badly formatted") == true ->
                                    "Email inválido"

                                exception.message?.contains("The given password is invalid.") == true ->
                                    "Senha fraca"

                                exception.message?.contains("The email address is already in use") == true ->
                                    "Email já cadastrado"

                                else -> exception.message.toString()
                            }
                            _toastMessage.value = RegisterErrorUiEvent.Error(message)
                            clearToastMessage()
                        }
                    }
                )
            } else {
                _toastMessage.value = RegisterErrorUiEvent.Error("Senha é obrigatória")
                clearToastMessage()
            }
        } else {
            _toastMessage.value = RegisterErrorUiEvent.Error("E-mail é obrigatório")
            clearToastMessage()
        }
    }

    private fun clearToastMessage() {
        viewModelScope.launch {
            /**
             * O stateFlow mantem o estado anterior, então não é necessário limpar o estado
             * pois se o mesmo erro ocorrer ele ja esta no estado anterior.
             * espera 2 segundos antes de limpar a mensagem, para a proxima ser exibida.
             */
            delay(2000)
            _toastMessage.value = RegisterErrorUiEvent()
        }
    }

    private fun setFormValues() {
        _uiState.update { registerUiState ->
            registerUiState.copy(
                onNameChanged = {
                    _uiState.value = _uiState.value.copy(name = it)
                },
                onEmailChanged = {
                    _uiState.value = _uiState.value.copy(email = it)
                },
                onPasswordChanged = {
                    _uiState.value = _uiState.value.copy(password = it)
                }
            )
        }
    }
}

