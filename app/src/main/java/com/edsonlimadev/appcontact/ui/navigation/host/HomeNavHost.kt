package com.edsonlimadev.appcontact.ui.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edsonlimadev.appcontact.ui.navigation.contactFormDestination
import com.edsonlimadev.appcontact.ui.navigation.detailsDestination
import com.edsonlimadev.appcontact.ui.navigation.navigateToContactForm
import com.edsonlimadev.appcontact.ui.navigation.navigateToDetails
import com.edsonlimadev.appcontact.ui.screens.contact.list.Contacts
import com.edsonlimadev.appcontact.ui.screens.contact.list.ContactsScreen

@Composable
fun HomeNavHost(
    onLogout: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Contacts
    ) {
        composable<Contacts> {
            ContactsScreen(
                navigateToDetails = {
                    navController.navigateToDetails()
                },
                navigateToContactForm = {
                    navController.navigateToContactForm()
                },
                onLogout = onLogout
            )
        }

        //todas as outras rotas que nao seja do menu
        detailsDestination()
        contactFormDestination(
            onClickBackToHome = {
                navController.popBackStack()
            }
        )

    }
}