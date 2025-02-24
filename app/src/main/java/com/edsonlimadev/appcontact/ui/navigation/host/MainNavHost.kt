package com.edsonlimadev.appcontact.ui.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.ui.navigation.Auth
import com.edsonlimadev.appcontact.ui.navigation.ContactsList
import com.edsonlimadev.appcontact.ui.navigation.authGraph
import com.edsonlimadev.appcontact.ui.navigation.contactFormDestination
import com.edsonlimadev.appcontact.ui.navigation.navigateToContactForm
import com.edsonlimadev.appcontact.ui.navigation.navigateToLogin
import com.edsonlimadev.appcontact.ui.screens.details.DetailsScreen
import com.edsonlimadev.appcontact.ui.screens.home.HomeScreen
import com.edsonlimadev.appcontact.utils.isUserLoggedIn
import kotlinx.serialization.Serializable

@Composable
fun NavHostScreen(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn()) ContactsList else Auth
    ) {

        authGraph(navController)

        composable<ContactsList> {
            HomeScreen(
                onLogout = {
                    navController.navigateToLogin()
                },
                navigateToContactForm = {
                    navController.navigateToContactForm()
                }
            )
        }


    }
}