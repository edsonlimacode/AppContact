package com.edsonlimadev.appcontact.domain.mapper

import com.edsonlimadev.appcontact.data.entities.ContactEntity
import com.edsonlimadev.appcontact.domain.model.Contact


fun ContactEntity.toDomain(): Contact {
    return Contact(
        id = id,
        name = name,
        number = number,
        address = address,
        addressNumber = addressNumber,
        neighborhood = neighborhood,
        city = city,
        uf = uf,
        favorite = favorite
    )
}

fun Contact.toEntity(): ContactEntity {
    return ContactEntity(
        id = id,
        name = name,
        number = number,
        address = address,
        addressNumber = addressNumber,
        neighborhood = neighborhood,
        city = city,
        uf = uf,
        favorite = favorite
    )
}