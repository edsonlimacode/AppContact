package com.edsonlimadev.appcontact.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.ui.screens.details.DetailsScreen
import kotlinx.serialization.Serializable

@Serializable
object Detail


fun NavGraphBuilder.detailsDestination() {

    composable<Detail> {
        DetailsScreen()
    }

}

fun NavController.navigateToDetails() {
    navigate(Detail)
}