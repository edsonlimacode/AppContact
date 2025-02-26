package com.edsonlimadev.appcontact.ui.screens.contact.form

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.components.CustomTextField
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray500

@Composable
fun ContactFormScreen(
    onClickBack: () -> Unit,
    onClickSave: (Contact) -> Unit = {},
    uiState: ContactFormUiState
) {

    var name by remember {
        mutableStateOf(uiState.contact?.name ?: "")
    }
    var number by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var addressNumber by remember {
        mutableStateOf("")
    }
    var neighborhood by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }
    var uf by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

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
                    value = number,
                    onValueChange = {
                        number = it
                    },
                    placeholder = "Numero"
                )
            }

            Spacer(Modifier.height(25.dp))

            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Endereço",
                fontSize = 18.sp,
                color = Gray500
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        value = address,
                        onValueChange = {
                            address = it
                        },
                        placeholder = "Rua"
                    )
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = addressNumber,
                        onValueChange = {
                            addressNumber = it
                        },
                        placeholder = "N°"
                    )
                }

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = neighborhood,
                    onValueChange = {
                        neighborhood = it
                    },
                    placeholder = "Bairro"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        value = city,
                        onValueChange = {
                            city = it
                        },
                        placeholder = "Cidade"
                    )
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = uf,
                        onValueChange = {
                            uf = it
                        },
                        placeholder = "UF"
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
                            if(number.isEmpty() || name.isEmpty()){
                                Toast.makeText(context, "Nome e numero são obrigatórios", Toast.LENGTH_SHORT).show()
                            }else {
                                val contact = Contact(
                                    name = name,
                                    number = number,
                                    address = address,
                                    addressNumber = addressNumber,
                                    neighborhood = neighborhood,
                                    city = city,
                                    uf = uf
                                )
                                onClickSave(contact)
                            }

                        }
                    ) {
                        Text(text = "Salvar", color = Gray500)
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
        onClickBack = {},
        uiState = ContactFormUiState()
    )
}