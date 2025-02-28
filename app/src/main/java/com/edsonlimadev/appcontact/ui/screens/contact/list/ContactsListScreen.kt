package com.edsonlimadev.appcontact.ui.screens.contact.list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.components.ContactListItem
import com.edsonlimadev.appcontact.ui.theme.Dark600
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray500
import com.edsonlimadev.appcontact.ui.theme.Gray600
import com.edsonlimadev.appcontact.ui.theme.Gray700
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsListScreen(
    navigateToDetail: (Contact) -> Unit = {},
    uiState: ContactListUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            value = uiState.search,
            onValueChange = {
                uiState.onSearchChance(it)
            },
            placeholder = {
                Text(text = "Pesquisa", color = Gray600)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Dark600,
                unfocusedContainerColor = Dark600,
                focusedTextColor = Gray500,
                unfocusedTextColor = Gray500,
                cursorColor = Gray500,
                focusedIndicatorColor = Color.Transparent,
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
                modifier = Modifier.fillMaxSize(),
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
                    if (uiState.search.isNotEmpty()) {
                        category to uiState.searchContacts?.filter {
                            it.name?.startsWith(category, true) ?: false
                        }
                    } else {
                        category to uiState.contacts?.filter {
                            it.name?.startsWith(category, true) ?: false
                        }
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
                            var isRemoved by remember {
                                mutableStateOf(false)
                            }

                            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
                                positionalThreshold = { distance ->
                                    distance * 0.5f
                                }
                            )

                            LaunchedEffect(swipeToDismissBoxState.currentValue) {
                                if (swipeToDismissBoxState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                                    isRemoved = true
                                    Log.i("removido", "ContactsListScreen: ${contact.name}")
                                }
                            }

                            AnimatedVisibility(
                                visible = !isRemoved,
                                exit = shrinkVertically(
                                    animationSpec = tween(durationMillis = 500),
                                    shrinkTowards = Alignment.Top
                                ) + fadeOut()
                            ) {
                                SwipeToDismissBox(
                                    state = swipeToDismissBoxState,
                                    backgroundContent = {
                                        val color =
                                            if (swipeToDismissBoxState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                                                Color.Red
                                            } else Color.Transparent

                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(color)
                                                .padding(horizontal = 16.dp),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }
                                    },
                                    enableDismissFromStartToEnd = false,
                                ) {
                                    ContactListItem(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Dark900),
                                        contact,
                                        navigateToDetail = {
                                            navigateToDetail(it)
                                        }
                                    )
                                }
                            }
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