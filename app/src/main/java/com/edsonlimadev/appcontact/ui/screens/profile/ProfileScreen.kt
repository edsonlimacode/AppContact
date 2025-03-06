package com.edsonlimadev.appcontact.ui.screens.profile

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.domain.model.Profile
import com.edsonlimadev.appcontact.ui.theme.Dark600
import com.edsonlimadev.appcontact.ui.theme.Dark800
import com.edsonlimadev.appcontact.ui.theme.Dark900
import com.edsonlimadev.appcontact.ui.theme.Gray500
import com.edsonlimadev.appcontact.ui.theme.Violet500
import com.edsonlimadev.appcontact.utils.getCurrentUser
import com.edsonlimadev.appcontact.utils.getFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onSaveToStorage: (Uri) -> Unit = {},
    uiState: ProfileUiState,
) {

    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
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

                when (uiState) {
                    is ProfileUiState.Loading -> {
                        CircularProgressIndicator(
                            color = Violet500
                        )
                    }

                    is ProfileUiState.Success -> {
                        AsyncImage(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape),
                            model = if (uiState.profile.image?.isNotEmpty() == true) Uri.parse(
                                uiState.profile.image
                            ) else R.drawable.default_avatar,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }

                    is ProfileUiState.Error -> {
                        Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .clickable {
                            showBottomSheet = !showBottomSheet
                        },
                    text = "Alterar foto",
                    fontSize = 14.sp,
                    color = Violet500
                )
                Text(
                    text = getCurrentUser().currentUser?.email.toString(), style = TextStyle(
                        color = Gray500,
                        fontSize = 18.sp
                    )
                )
            }

            val pickMedia = rememberLauncherForActivityResult(
                ActivityResultContracts.PickVisualMedia()
            ) { uri ->
                if (uri != null) {
                    showBottomSheet = false
                    onSaveToStorage(uri)
                } else {
                    Toast.makeText(context, "Selecione uma imagem", Toast.LENGTH_SHORT).show()
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                    containerColor = Dark600
                ) {
                    Row(
                        modifier = Modifier
                            .heightIn(min = 100.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Dark800
                                ),
                                onClick = {
                                    pickMedia.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                }) {
                                Icon(
                                    modifier = Modifier.padding(10.dp),
                                    painter = painterResource(R.drawable.ic_add_photo_gallery),
                                    contentDescription = null,
                                    tint = Violet500
                                )
                            }
                            Text(
                                text = "Galeria", style = TextStyle(
                                    color = Gray500,
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Column {
                            IconButton(
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Dark800
                                ),
                                onClick = {}) {
                                Icon(
                                    modifier = Modifier.padding(10.dp),
                                    painter = painterResource(R.drawable.ic_add_a_photo),
                                    contentDescription = null,
                                    tint = Violet500
                                )
                            }
                            Text(
                                text = "Camera", style = TextStyle(
                                    color = Gray500,
                                    fontSize = 14.sp
                                )
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
private fun ProfileScreenPreview() {
    ProfileScreen(
        modifier = Modifier,
        uiState = ProfileUiState.Loading
    )
}