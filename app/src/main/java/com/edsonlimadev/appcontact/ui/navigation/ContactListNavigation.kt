package com.edsonlimadev.appcontact.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.ui.screens.contact.home.ContactsScreen
import kotlinx.serialization.Serializable

@Serializable
object Contacts

fun NavGraphBuilder.contactListDestination(
    onLogout: () -> Unit,
    navigateToContactForm: () -> Unit,
    navigateToDetails: () -> Unit,
) {
    composable<Contacts> {
        ContactsScreen(
            navigateToDetails = navigateToDetails,
            navigateToContactForm = navigateToContactForm,
            onLogout = onLogout
        )
    }
}

fun NavController.navigateToContactsHome() {
    navigate(Contacts)
}