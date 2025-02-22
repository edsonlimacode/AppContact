package com.edsonlimadev.appcontact.ui.screens.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.ui.theme.Dark600
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray500
import com.edsonlimadev.appcontact.ui.theme.Gray600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back_24),
                        contentDescription = null,
                        tint = Gray500
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Dark900
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Dark900
            ) {
                NavigationBarItem(
                    label = {
                        Text(text = "Favoritos", color = Gray600)
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_heart_add),
                            contentDescription = null,
                            tint = Gray500
                        )
                    }
                )
                NavigationBarItem(
                    label = {
                        Text(text = "Compartilhar", color = Gray600)
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_share),
                            contentDescription = null,
                            tint = Gray500
                        )
                    }
                )
                NavigationBarItem(
                    label = {
                        Text(text = "Editar", color = Gray600)
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = null,
                            tint = Gray500
                        )
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Dark600
                )
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .size(80.dp)
                            .clip(CircleShape),
                        model = R.drawable.screen,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Edson Lima", style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "(88) 99990-9999", style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        expanded = !expanded
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Dark600,
                    contentColor = Gray500
                )
            ) {

                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Endereço",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(8.dp)
                        )

                        if (expanded) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_down),
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_right),
                                contentDescription = null
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = expanded,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        if (expanded) {
                            Text(
                                text = "Content Sample for Display on Expansion of Card",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPrev() {
    DetailsScreen()
}