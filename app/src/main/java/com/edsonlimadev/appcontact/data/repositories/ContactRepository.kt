package com.edsonlimadev.appcontact.data.repositories

import com.edsonlimadev.appcontact.data.dao.ContactDao
import com.edsonlimadev.appcontact.data.entities.ContactEntity
import com.edsonlimadev.appcontact.domain.repositories.IContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) : IContactRepository {
    override fun getAllContacts(): Flow<List<ContactEntity>?> {
        return contactDao.getAllContacts()
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
        contactDao.upadate(contact)
    }

    override suspend fun deleteContact(contact: ContactEntity) {
        contactDao.delete(contact)
    }

    override suspend fun addToFavorite(contact: ContactEntity) {
        if (contact.favorite) {
            contactDao.addToFavorite(contact.id, false)
        } else {
            contactDao.addToFavorite(contact.id, true)
        }
    }
}