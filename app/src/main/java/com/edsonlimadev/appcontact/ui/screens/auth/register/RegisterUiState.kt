package com.edsonlimadev.appcontact.ui.screens.auth.register

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val onNameChanged: (String) -> Unit = {},
    val onEmailChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {}
)