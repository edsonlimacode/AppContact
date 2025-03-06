package com.edsonlimadev.appcontact.ui.screens.favorite

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.components.FavoriteListItem
import com.edsonlimadev.appcontact.ui.theme.Gray600

@Composable
fun FavoritesScreen(
    uiState: FavoriteListUiState,
    navigateToDetail: (Contact) -> Unit = {},
    removeFavorite: (Contact) -> Unit = {}
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (uiState.contactsFavorites?.isEmpty() == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Nenhum contato encontrado", style = TextStyle(
                        color = Gray600,
                        fontSize = 18.sp
                    )
                )
            }
        } else {



            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
            ) {

                uiState.contactsFavorites?.let { contacts ->
                    items(items = contacts, key = { it.id }) { contact ->

                        var isVisible by remember {
                            mutableStateOf(true)
                        }

                        AnimatedVisibility(
                            visible = isVisible,
                            exit = fadeOut()
                        ) {
                            FavoriteListItem(
                                modifier = Modifier.fillMaxWidth(),
                                contact,
                                navigateToDetail = {
                                    navigateToDetail(it)
                                },
                                removeFavorite = {
                                    removeFavorite(it)
                                    isVisible = false
                                }
                            )
                        }

                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContactsScreenPrev() {
    FavoritesScreen(
        uiState = FavoriteListUiState(),
        navigateToDetail = { }
    )
}