package com.edsonlimadev.appcontact.extensions

import android.content.Context
import android.content.Intent
import com.edsonlimadev.appcontact.domain.model.Contact

fun Context.shareFileWith(contact: Contact) {

    val share = "Nome: ${contact.name} \nNumero: ${contact.number}"

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, share)
        type = "text/plain"
    }
    startActivity(Intent.createChooser(shareIntent, "Compartilhar com"))
}