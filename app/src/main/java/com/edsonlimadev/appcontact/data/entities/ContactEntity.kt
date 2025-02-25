package com.edsonlimadev.appcontact.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String? = "",
    var number: String? = "",
    var address: String? = "",
    var addressNumber: String? = "",
    var neighborhood: String? = "",
    var city: String? = "",
    var uf: String? = "",
    var favorite: Boolean = false
)