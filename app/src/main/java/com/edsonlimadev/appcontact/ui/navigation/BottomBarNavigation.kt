package com.edsonlimadev.appcontact.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.screens.contact.list.ContactListViewModel
import com.edsonlimadev.appcontact.ui.screens.contact.list.ContactsListScreen
import com.edsonlimadev.appcontact.ui.screens.favorite.FavoriteViewModel
import com.edsonlimadev.appcontact.ui.screens.favorite.FavoritesScreen
import com.edsonlimadev.appcontact.ui.screens.profile.ProfileScreen
import com.edsonlimadev.appcontact.ui.screens.profile.ProfileViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object ContactsList

@Serializable
object Profile

@Serializable
object Favorite

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.bottomBarDestination(
    navigateToDetail: (Contact) -> Unit
) {

    composable<ContactsList> {

        val contactListViewModel = hiltViewModel<ContactListViewModel>()
        val uiState by contactListViewModel.uiState.collectAsStateWithLifecycle()

        ContactsListScreen(
            uiState = uiState,
            navigateToDetail = { contact ->
                navigateToDetail(contact)
            },
            onDelete = {
                contactListViewModel.delete(it)
            }
        )
    }

    composable<Profile> {

        val profileViewModel = hiltViewModel<ProfileViewModel>()
        val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()

        ProfileScreen(
            uiState = uiState,
            onSaveToStorage = {
                profileViewModel.saveImageProfile(it)
            }
        )
    }

    composable<Favorite> {

        val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
        val uiState by favoriteViewModel.uiState.collectAsStateWithLifecycle()

        FavoritesScreen(
            uiState = uiState,
            navigateToDetail = { contact ->
                navigateToDetail(contact)
            },
            removeFavorite = {
                favoriteViewModel.removeFavorite(it)
            }
        )
    }
}

fun NavController.navigateToHome() {
    navigate(Home) {
        launchSingleTop = true
        popUpTo<Login> {
            inclusive = true
        }
    }
}

fun NavController.navigateToContactsList() {
    navigate(ContactsList) {
        launchSingleTop = true
        popUpTo<ContactsList> {
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