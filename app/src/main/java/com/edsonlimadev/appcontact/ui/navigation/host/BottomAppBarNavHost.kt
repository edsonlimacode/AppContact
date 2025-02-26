package com.edsonlimadev.appcontact.ui.navigation.host

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.navigation.ContactsList
import com.edsonlimadev.appcontact.ui.navigation.bottomBarDestination

@Composable
fun BottomAppBarNavHost(
    navController: NavHostController,
    navigateToDetails: (Contact) -> Unit = {},
) {

    NavHost(
        modifier = Modifier.padding(16.dp),
        navController = navController,
        startDestination = ContactsList
    ) {
        bottomBarDestination(
            navigateToDetail = { contact ->
                navigateToDetails(contact)
            }
        )
    }
}