package com.edsonlimadev.appcontact.ui.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.appcontact.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _toastMessage = MutableStateFlow(LoginErrorUiEvent())
    val toastMessage: StateFlow<LoginErrorUiEvent> = _toastMessage.asStateFlow()


    init {
        setFormValues()
    }

    fun login() = viewModelScope.launch {

        if (_uiState.value.email.isNotEmpty()) {

            if (_uiState.value.password.isNotEmpty()) {

                loginUseCase(uiState.value.email, uiState.value.password).fold(
                    onSuccess = {
                        viewModelScope.launch {
                            _toastMessage.value =
                                LoginErrorUiEvent.Success("Cadastro realizado com sucesso")
                            clearToastMessage()
                        }
                    },
                    onFailure = { exception ->
                        viewModelScope.launch {
                            val message = when {
                                exception.message?.contains("supplied auth credential is incorrect") == true ->
                                    "Email ou Senha inválido"
                                else -> exception.message.toString()
                            }
                            _toastMessage.value = LoginErrorUiEvent.Error(message)
                            clearToastMessage()
                        }
                    }
                )
            } else {
                _toastMessage.value = LoginErrorUiEvent.Error("Senha é obrigatória")
                clearToastMessage()
            }
        } else {
            _toastMessage.value = LoginErrorUiEvent.Error("E-mail é obrigatório")
            clearToastMessage()
        }
    }

    private fun setFormValues() {
        _uiState.update { loginUiState ->
            loginUiState.copy(
                onEmailChanged = {
                    _uiState.value = _uiState.value.copy(email = it)
                },
                onPasswordChanged = {
                    _uiState.value = _uiState.value.copy(password = it)
                }
            )
        }
    }

    private fun clearToastMessage() {
        viewModelScope.launch {
            delay(2000)
            _toastMessage.value = LoginErrorUiEvent()
        }
    }
}

