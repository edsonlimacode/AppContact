package com.edsonlimadev.appcontact.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.ui.screens.contact.form.ContactFormScreen
import com.edsonlimadev.appcontact.ui.screens.contact.form.ContactFormViewModel
import kotlinx.serialization.Serializable

@Serializable
data class ContactForm(val id: Long? = null)


fun NavGraphBuilder.contactFormDestination(
    onClickBackToHome: () -> Unit = {}
) {

    composable<ContactForm>(
        enterTransition = { slideInHorizontally { it } },
        exitTransition = {  slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {

        val contactFormViewModel = hiltViewModel<ContactFormViewModel>()
        val uiState by contactFormViewModel.uiState.collectAsStateWithLifecycle()

        ContactFormScreen(
            uiState = uiState,
            onClickBack = onClickBackToHome,
            onClickSave = { contact ->
                contactFormViewModel.insert(contact)
                onClickBackToHome()
            }
        )
    }
}


fun NavController.navigateToContactForm(id: Long? = null) {
    navigate(ContactForm(id))
}