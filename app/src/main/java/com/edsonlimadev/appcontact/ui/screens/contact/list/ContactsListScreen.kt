package com.edsonlimadev.appcontact.ui.screens.contact.list

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
import com.edsonlimadev.appcontact.ui.components.ContactListItem
import com.edsonlimadev.appcontact.ui.theme.Dark600
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray600
import com.edsonlimadev.appcontact.ui.theme.Gray700

@Composable
fun ContactsListScreen(
    navigateToDetail: () -> Unit = {},
    uiState: ContactListUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark900)
    ) {
        TextField(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(text = "Pesquisa", color = Gray600)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Dark600,
                unfocusedContainerColor = Dark600,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search), contentDescription = "",
                    tint = Gray700,
                )
            }
        )

        if (uiState.contacts?.isEmpty() == true) {
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
                modifier = Modifier.fillMaxWidth(),
            ) {

                val group = listOf(
                    "A",
                    "B",
                    "C",
                    "D",
                    "E",
                    "F",
                    "G",
                    "H",
                    "I",
                    "J",
                    "K",
                    "L",
                    "M",
                    "N",
                    "O",
                    "P",
                    "Q",
                    "R",
                    "S",
                    "T",
                    "U",
                    "V",
                    "W",
                    "X",
                    "Y",
                    "Z"
                )

                val contactsList = group.map { category ->
                    category to uiState.contacts?.filter {
                        it.name?.startsWith(category, true) ?: false
                    }
                }

                items(items = contactsList, key = { it }) {

                    val title = it.first
                    val contacts = it.second

                    if (contacts?.isNotEmpty() == true) {
                        Text(
                            modifier = Modifier.padding(bottom = 10.dp),
                            text = title,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        )
                        contacts.forEach { contact ->
                            ContactListItem(
                                modifier = Modifier.fillMaxWidth(),
                                contact,
                                navigateToDetail = {
                                    navigateToDetail()
                                }
                            )
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContactsListScreenPreview() {
    ContactsListScreen(uiState = ContactListUiState())
}