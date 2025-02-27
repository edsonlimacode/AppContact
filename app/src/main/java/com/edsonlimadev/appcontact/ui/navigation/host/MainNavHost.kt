package com.edsonlimadev.appcontact.ui.navigation.host

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edsonlimadev.appcontact.ui.navigation.Auth
import com.edsonlimadev.appcontact.ui.navigation.Home
import com.edsonlimadev.appcontact.ui.navigation.authGraph
import com.edsonlimadev.appcontact.ui.navigation.navigateToHome
import com.edsonlimadev.appcontact.ui.navigation.navigateToLogin
import com.edsonlimadev.appcontact.ui.screens.home.HomeScreen
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.utils.isUserLoggedIn

@Composable
fun MainNavHost() {

    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn()) Home else Auth
    ) {

        authGraph(navController)

        composable<Home> {
            HomeScreen(
                onLogout = {
                    navController.navigateToLogin()
                }
            )
        }
    }
}