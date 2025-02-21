package com.edsonlimadev.appcontact.ui.screens.auth.login

open class LoginErrorUiEvent {
    data class Success(val message: String? = null) : LoginErrorUiEvent()
    data class Error(val message: String? = null) : LoginErrorUiEvent()
}