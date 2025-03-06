package com.edsonlimadev.appcontact.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.screens.details.DetailsScreen
import com.edsonlimadev.appcontact.ui.screens.details.DetailsViewModel
import kotlinx.serialization.Serializable

@Serializable
data class Detail(val id: Long)


fun NavGraphBuilder.detailsDestination(
    navigateToContactForm: (Contact) -> Unit,
    onClickBack: () -> Unit
) {

    composable<Detail>(
        enterTransition = { slideInHorizontally { it } },
        exitTransition = {  slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it }}
    ) {

        val detailsViewModel = hiltViewModel<DetailsViewModel>()
        val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()

        uiState.contact?.let { contact ->
            DetailsScreen(
                navigateToContactForm = {
                        navigateToContactForm(it)
                },
                contact = contact,
                onClickBack = onClickBack,
                addToFavorite = {
                    detailsViewModel.addToFavorite(it)
                }
            )
        }
    }

}

fun NavController.navigateToDetails(id: Long) {
    navigate(Detail(id))
}