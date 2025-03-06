package com.edsonlimadev.appcontact.ui.screens.contact.form

import android.widget.Toast
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
import com.edsonlimadev.appcontact.ui.theme.Gray500

@Composable
fun ContactFormScreen(
    onClickBack: () -> Unit,
    onClickSave: (Contact) -> Unit = {},
    uiState: ContactFormUiState
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    value = uiState.name,
                    onValueChange = {
                        uiState.onNameChange(it)
                    },
                    placeholder = "Nome"
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.number,
                    onValueChange = {
                        uiState.onNumberChange(it)
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
                        value = uiState.address,
                        onValueChange = {
                            uiState.onAddressChange(it)
                        },
                        placeholder = "Rua"
                    )
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = uiState.addressNumber,
                        onValueChange = {
                            uiState.onAddressNumberChange(it)
                        },
                        placeholder = "N°"
                    )
                }

                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.neighborhood,
                    onValueChange = {
                        uiState.onNeighborhoodChange(it)
                    },
                    placeholder = "Bairro"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        value = uiState.city,
                        onValueChange = {
                            uiState.onCityChange(it)
                        },
                        placeholder = "Cidade"
                    )
                    CustomTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = uiState.uf,
                        onValueChange = {
                            uiState.onUfChange(it)
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
                            if (uiState.number.isEmpty() || uiState.name?.isEmpty() == true) {
                                Toast.makeText(
                                    context,
                                    "Nome e numero são obrigatórios",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val contact = Contact(
                                    id = uiState.id,
                                    name = uiState.name,
                                    number = uiState.number,
                                    address = uiState.address,
                                    addressNumber = uiState.addressNumber,
                                    neighborhood = uiState.neighborhood,
                                    city = uiState.city,
                                    uf = uiState.uf
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