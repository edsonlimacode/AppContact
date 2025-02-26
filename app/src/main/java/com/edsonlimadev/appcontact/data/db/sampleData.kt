package com.edsonlimadev.appcontact.data.db

import com.edsonlimadev.appcontact.domain.model.Contact


val contactsSample = listOf(
    Contact(
        2,
        "Alex michael",
        "889922325",
        "Av. sete",
        "980",
        "Jardim seven",
        "Fortaleza",
        "CE",
    ),
    Contact(
        2,
        "Ben michael",
        "889922325",
        "Av. sete",
        "S/N",
        "Jardim seven",
        "São Paulo",
        "SP",
    ),
    Contact(
        3,
        "Dany michael",
        "889922325",
        "Av. sete",
        "S/N",
        "Jardim seven",
        "São Paulo",
        "SP",
    ),
    Contact(
        4,
        "Any michael",
        "889922325",
        "Av. sete",
        "S/N",
        "Jardim seven",
        "São Paulo",
        "SP",
    )
)

/*val categorySample = listOf(
    "A",
    "B",
    "C",
    "D",
)*/

/*
val favoriteContactsSample = mapOf(
    "A" to contactsSample.filter { it.name?.startsWith("A", true) ?: false },
    "B" to contactsSample.filter { it.name?.startsWith("B", true) ?: false },
    "C" to contactsSample.filter { it.name?.startsWith("C", true) ?: false }
)*/
