package com.edsonlimadev.appcontact.ui.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.edsonlimadev.appcontact.ui.navigation.ContactsList
import com.edsonlimadev.appcontact.ui.navigation.homeDestination

@Composable
fun BottomAppBarNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ContactsList
    ) {
        homeDestination()
    }
}