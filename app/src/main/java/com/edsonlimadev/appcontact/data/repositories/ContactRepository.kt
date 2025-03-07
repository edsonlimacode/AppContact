package com.edsonlimadev.appcontact.data.repositories

import com.edsonlimadev.appcontact.data.dao.ContactDao
import com.edsonlimadev.appcontact.data.entities.ContactEntity
import com.edsonlimadev.appcontact.domain.repositories.IContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) : IContactRepository {
    override fun getAllContacts(userId: String): Flow<List<ContactEntity>?> {
        return contactDao.getAllContacts(userId)
    }

    override fun getAllFavorites(): Flow<List<ContactEntity>?> {
        return contactDao.getFavorites()
    }

    override suspend fun insertContact(contact: ContactEntity) {
        contactDao.insert(contact)
    }

    override suspend fun getContactById(id: Long): ContactEntity {
        return contactDao.findById(id)
    }

    override suspend fun updateContact(contact: ContactEntity) {
        contactDao.update(contact)
    }

    override suspend fun deleteContact(contact: ContactEntity) {
        contactDao.delete(contact)
    }

    override suspend fun addToFavorite(contact: ContactEntity) {
        contactDao.addOrRemoveFavorite(contact.id, true)
    }

    override suspend fun removeFavorite(contact: ContactEntity) {
        contactDao.addOrRemoveFavorite(contact.id, false)
    }
}