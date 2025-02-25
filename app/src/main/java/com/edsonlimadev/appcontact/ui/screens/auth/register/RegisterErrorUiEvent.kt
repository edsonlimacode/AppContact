package com.edsonlimadev.appcontact.ui.screens.auth.register

open class RegisterErrorUiEvent {
    data class Success(val message: String?="") : RegisterErrorUiEvent()
    data class Error(val message: String?="") : RegisterErrorUiEvent()
}