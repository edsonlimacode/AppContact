package com.edsonlimadev.appcontact.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.screens.contact.home.ContactsScreen
import kotlinx.serialization.Serializable

@Serializable
object Contacts

fun NavGraphBuilder.contactListDestination(
    onLogout: () -> Unit,
    navigateToContactForm: () -> Unit,
    navigateToDetails: (Contact) -> Unit,
) {
    composable<Contacts> {
        ContactsScreen(
            navigateToDetails = {
                navigateToDetails(it)
            },
            navigateToContactForm = navigateToContactForm,
            onLogout = onLogout
        )
    }
}

fun NavController.navigateToContactsHome() {
    navigate(Contacts){
        launchSingleTop = true
    }
}