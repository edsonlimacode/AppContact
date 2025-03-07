package com.edsonlimadev.appcontact.domain.repositories

import com.edsonlimadev.appcontact.data.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

interface IContactRepository {
    fun getAllContacts(userId: String): Flow<List<ContactEntity>?>
    fun getAllFavorites(): Flow<List<ContactEntity>?>
    suspend fun insertContact(contact: ContactEntity)
    suspend fun getContactById(id: Long): ContactEntity
    suspend fun updateContact(contact: ContactEntity)
    suspend fun deleteContact(contact: ContactEntity)
    suspend fun addToFavorite(contact: ContactEntity)
    suspend fun removeFavorite(contact: ContactEntity)
}