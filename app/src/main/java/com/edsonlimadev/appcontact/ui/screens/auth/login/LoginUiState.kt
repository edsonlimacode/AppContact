package com.edsonlimadev.appcontact.ui.screens.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val onEmailChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {}
)