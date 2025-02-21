package com.edsonlimadev.appcontact.data.db

import com.edsonlimadev.appcontact.domain.model.Contact


val contactsSample = listOf(
    Contact(
        "Andy joe",
        "889922325",
        "john@gmail.com",
        "Av. sete",
        980,
        "Jardim seven",
        "São Paulo",
        "SP"

    ),
    Contact(
        "Ben michael",
        "889922325",
        "john@gmail.com",
        "Av. sete",
        980,
        "Jardim seven",
        "São Paulo",
        "SP"

    ),
    Contact(
        "Ace johnny",
        "889922325",
        "john@gmail.com",
        "Av. sete",
        980,
        "Jardim seven",
        "São Paulo",
        "SP"
    ),Contact(
        "Eliot james",
        "889922325",
        "john@gmail.com",
        "Av. sete",
        980,
        "Jardim seven",
        "São Paulo",
        "SP"
    ),
)

val favoriteContactsSample = mapOf(
    "A" to contactsSample.filter { it.name.startsWith("A", true) },
    "B" to contactsSample.filter { it.name.startsWith("B", true) },
    "C" to contactsSample.filter { it.name.startsWith("C", true) },
    "D" to contactsSample.filter { it.name.startsWith("d", true) },
    "E" to contactsSample.filter { it.name.startsWith("e", true) }
)