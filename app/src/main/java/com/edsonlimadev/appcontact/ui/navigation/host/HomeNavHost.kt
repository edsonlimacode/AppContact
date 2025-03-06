package com.edsonlimadev.appcontact.ui.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.edsonlimadev.appcontact.ui.navigation.Contacts
import com.edsonlimadev.appcontact.ui.navigation.contactFormDestination
import com.edsonlimadev.appcontact.ui.navigation.contactListDestination
import com.edsonlimadev.appcontact.ui.navigation.detailsDestination
import com.edsonlimadev.appcontact.ui.navigation.navigateToContactForm
import com.edsonlimadev.appcontact.ui.navigation.navigateToContactsHome
import com.edsonlimadev.appcontact.ui.navigation.navigateToDetails

@Composable
fun HomeNavHost(
    onLogout: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Contacts
    ) {
        contactListDestination(
            onLogout = onLogout,
            navigateToContactForm = {
                navController.navigateToContactForm()
            },
            navigateToDetails = { contact ->
                navController.navigateToDetails(contact.id)
            }
        )
        detailsDestination(
            navigateToContactForm = { contact ->
                navController.navigateToContactForm(contact.id)
            },
            onClickBack = {
                navController.popBackStack()
            }
        )
        contactFormDestination(
            onClickBackToHome = {
                navController.navigateToContactsHome()
            }
        )
    }
}