package com.edsonlimadev.appcontact.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.ui.components.BottomNavigationBar
import com.edsonlimadev.appcontact.ui.components.NavigationBarItem
import com.edsonlimadev.appcontact.ui.navigation.host.BottomAppBarNavHost
import com.edsonlimadev.appcontact.ui.navigation.navigateToFavorite
import com.edsonlimadev.appcontact.ui.navigation.navigateToHome
import com.edsonlimadev.appcontact.ui.navigation.navigateToProfile
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.utils.getCurrentUser

const val CONTACTS_LIST = "ContactsList"
const val FAVORITE = "Favorite"
const val PROFILE = "Profile"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit = {},
    navController: NavHostController = rememberNavController()
) {

    val backStackEntryState by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntryState?.destination?.route
    val route = currentRoute?.substringAfterLast(".")

    var itemSelected by remember {
        mutableStateOf<NavigationBarItem>(NavigationBarItem.Home)
    }

    val isShowBottomAndTopBar = when (route) {
        CONTACTS_LIST,
        FAVORITE,
        PROFILE -> true

        else -> false
    }

    Scaffold(
        topBar = {
            if (isShowBottomAndTopBar) {
                TopAppBar(
                    title = {
                        Text(text = if (itemSelected == NavigationBarItem.Home) "Contatos" else "")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Dark900,
                        titleContentColor = Color.White
                    ),
                    actions = {
                        IconButton(
                            onClick = {
                                getCurrentUser().signOut()
                                onLogout()
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_logout),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (isShowBottomAndTopBar) {
                BottomNavigationBar(
                    selectedItem = itemSelected,
                    onClickNavigate = { navigationBarItem ->
                        when (navigationBarItem) {
                            NavigationBarItem.Home -> {
                                itemSelected = navigationBarItem
                                navController.navigateToHome()
                            }

                            NavigationBarItem.Favorite -> {
                                itemSelected = navigationBarItem
                                navController.navigateToFavorite()
                            }

                            NavigationBarItem.Profile -> {
                                itemSelected = navigationBarItem
                                navController.navigateToProfile()
                            }
                        }
                    }
                )
            }

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Dark900)
        ) {
            BottomAppBarNavHost(navController)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}