package com.edsonlimadev.appcontact.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.edsonlimadev.appcontact.ui.screens.auth.login.LoginScreen
import com.edsonlimadev.appcontact.ui.screens.auth.login.LoginViewModel
import com.edsonlimadev.appcontact.ui.screens.auth.register.RegisterScreen
import com.edsonlimadev.appcontact.ui.screens.auth.register.RegisterViewModel
import kotlinx.serialization.Serializable

@Serializable
object Auth

@Serializable
object Login

@Serializable
object Register


fun NavGraphBuilder.authGraph(
    navController: NavController
) {

    navigation<Auth>(
        startDestination = Login
    ) {


        composable<Login>(
            enterTransition = { slideInHorizontally { it } },
            exitTransition = {  slideOutHorizontally { -it } },
            popEnterTransition = { slideInHorizontally { -it } },
            popExitTransition = { slideOutHorizontally { it } }
        ) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
            val toastMessage by loginViewModel.toastMessage.collectAsStateWithLifecycle()

            LoginScreen(
                uiState = uiState,
                toastMessage = toastMessage,
                onClickLogin = {
                    loginViewModel.login()
                },
                navigateToRegister = {
                    navController.navigateToRegister()
                },
                navigateToHome = {
                    navController.navigateToHome()
                }

            )
        }

        composable<Register>(
            enterTransition = { slideInHorizontally { it } },
            exitTransition = {  slideOutHorizontally { -it } },
            popEnterTransition = { slideInHorizontally { -it } },
            popExitTransition = { slideOutHorizontally { it }}
        ) {

            val authViewModel = hiltViewModel<RegisterViewModel>()
            val uiState by authViewModel.uiState.collectAsStateWithLifecycle()
            val toastMessage by authViewModel.toastMessage.collectAsStateWithLifecycle()

            RegisterScreen(
                uiState = uiState,
                toastMessage = toastMessage,
                onRegister = {
                    authViewModel.register()
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}

fun NavController.navigateToLogin() {
    navigate(Login) {
        popUpTo<ContactsList> {
            inclusive = true
        }
    }
}

fun NavController.navigateToRegister() {
    navigate(Register)
}