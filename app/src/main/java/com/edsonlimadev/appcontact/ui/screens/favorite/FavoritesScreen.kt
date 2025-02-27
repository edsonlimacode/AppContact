package com.edsonlimadev.appcontact.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.components.ContactListItem
import com.edsonlimadev.appcontact.ui.components.FavoriteListItem
import com.edsonlimadev.appcontact.ui.theme.Dark600
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray600
import com.edsonlimadev.appcontact.ui.theme.Gray700

@Composable
fun FavoritesScreen(
    uiState: FavoriteListUiState,
    navigateToDetail: (Contact) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark900)
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
                        FavoriteListItem(
                            modifier = Modifier.fillMaxWidth(),
                            contact,
                            navigateToDetail = {
                                navigateToDetail(it)
                            }
                        )
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
        navigateToDetail = {  }
    )
}