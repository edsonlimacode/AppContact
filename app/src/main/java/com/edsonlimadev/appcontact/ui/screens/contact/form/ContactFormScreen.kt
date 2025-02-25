package com.edsonlimadev.appcontact.ui.screens.contact.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.ui.components.CustomTextField
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray500
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ContactFormScreen(
    onClickBack: () -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark900)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 80.dp, bottom = 50.dp)
                        .size(80.dp)
                        .clip(CircleShape),
                    model = R.drawable.screen,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Contato",
                fontSize = 18.sp,
                color = Gray500
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = "Nome"
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = "Nome"
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = "Nome"
                )
            }

            Spacer(Modifier.height(25.dp))

            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Contato",
                fontSize = 18.sp,
                color = Gray500
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = "Nome"
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = "Nome"
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = "Nome"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(0.7f),
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        placeholder = "Nome"
                    )
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        placeholder = "Nome"
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(
                        modifier = Modifier.height(48.dp),
                        onClick = onClickBack

                    ) {
                        Text(text = "Cancelar", color = Gray500)
                    }
                    TextButton(
                        modifier = Modifier.height(48.dp),
                        onClick = {
                        }
                    ) {
                        Text(text = "Savar", color = Gray500)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ContactFormPrev() {
    ContactFormScreen(
        onClickBack = {}
    )
}