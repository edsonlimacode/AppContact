package com.edsonlimadev.appcontact.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.ui.screens.contact.form.ContactFormScreen
import kotlinx.serialization.Serializable

@Serializable
object ContactForm


fun NavGraphBuilder.contactFormDestination(
    onClickBackToHome: () -> Unit = {}
) {

    composable<ContactForm> {
        ContactFormScreen(
            onClickBack = onClickBackToHome
        )
    }
}


fun NavController.navigateToContactForm() {
    navigate(ContactForm)
}