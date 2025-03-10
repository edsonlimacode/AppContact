package com.edsonlimadev.appcontact.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.edsonlimadev.appcontact.domain.model.Contact
import com.edsonlimadev.appcontact.ui.theme.Violet500


@Composable
fun ContactListItem(
    modifier: Modifier, contact: Contact,
    navigateToDetail: (Contact) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navigateToDetail(contact)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (contact.avatar?.isNotEmpty() == true) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                model = R.drawable.screen,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Violet500)
                    .size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.name?.first()?.uppercase().toString(),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

        contact.name?.let {
            Text(
                text = it, fontSize = 20.sp, color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun ContactListItemPrev() {
    ContactListItem(
        contact = Contact(
            2,
            "Alex michael",
            "889922325"
        ),
        navigateToDetail = {},
        modifier = Modifier.fillMaxWidth()
    )
}