package com.edsonlimadev.appcontact.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.ui.screens.favorite.FavoritesScreen
import com.edsonlimadev.appcontact.ui.screens.contact.list.ContactsListScreen
import com.edsonlimadev.appcontact.ui.screens.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
object ContactsList

@Serializable
object Profile

@Serializable
object Favorite

fun NavGraphBuilder.homeDestination() {
    composable<ContactsList> {
        ContactsListScreen()
    }

    composable<Profile> {
        ProfileScreen()
    }

    composable<Favorite> {
        FavoritesScreen()
    }
}

fun NavController.navigateToHome() {
    navigate(ContactsList) {
        launchSingleTop = true
        popUpTo<Login> {
            inclusive = true
        }
    }
}

fun NavController.navigateToFavorite() {
    navigate(Favorite) {
        launchSingleTop = true
        popUpTo<Favorite> {
            inclusive = true
        }
    }
}

fun NavController.navigateToProfile() {
    navigate(Profile) {
        launchSingleTop = true
        popUpTo<Profile> {
            inclusive = true
        }
    }
}