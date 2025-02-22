package com.edsonlimadev.appcontact.ui.screens.contact.list

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.data.db.favoriteContactsSample
import com.edsonlimadev.appcontact.ui.components.ContactListItem
import com.edsonlimadev.appcontact.ui.theme.Dark600
import com.edsonlimadev.appcontact.ui.theme.Gray600
import com.edsonlimadev.appcontact.ui.theme.Gray700

@Composable
fun ContactsListScreen(
    navigateToDetail: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(items = favoriteContactsSample.toList(), key = { it.first }) {
                val title = it.first
                val contacts = it.second

                if (contacts.isNotEmpty()) {
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

@Preview
@Composable
private fun ContactsListScreenPreview() {
    ContactsListScreen()
}