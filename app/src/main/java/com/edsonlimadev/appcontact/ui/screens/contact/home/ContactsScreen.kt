package com.edsonlimadev.appcontact.ui.screens.contact.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.components.BottomNavigationBar
import com.edsonlimadev.appcontact.ui.components.NavigationBarItem
import com.edsonlimadev.appcontact.ui.navigation.host.BottomAppBarNavHost
import com.edsonlimadev.appcontact.ui.navigation.navigateToContactsList
import com.edsonlimadev.appcontact.ui.navigation.navigateToFavorite
import com.edsonlimadev.appcontact.ui.navigation.navigateToProfile
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Violet500
import com.edsonlimadev.appcontact.utils.getCurrentUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    navigateToContactForm: () -> Unit,
    navigateToDetails: (Contact) -> Unit,
    onLogout: () -> Unit
) {

    val navController: NavHostController = rememberNavController()

    var itemSelected by remember {
        mutableStateOf<NavigationBarItem>(NavigationBarItem.Home)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Contatos")
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
        },
        bottomBar = {
            BottomNavigationBar(
                selectedItem = itemSelected,
                onClickNavigate = { navigationBarItem ->
                    when (navigationBarItem) {
                        NavigationBarItem.Home -> {
                            itemSelected = navigationBarItem
                            navController.navigateToContactsList()
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
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Violet500,
                onClick = navigateToContactForm
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add), tint = Color.White,
                    contentDescription = null
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
            BottomAppBarNavHost(
                navController = navController,
                navigateToDetails = {
                    navigateToDetails(it)
                }
            )
        }
    }
}