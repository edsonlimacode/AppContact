package com.edsonlimadev.appcontact.domain.usecase.contact

import com.edsonlimadev.appcontact.data.repositories.ContactRepository
import com.edsonlimadev.appcontact.domain.mapper.toEntity
import com.edsonlimadev.appcontact.domain.model.Contact
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(contact: Contact): Result<Unit> {
        try {
            contactRepository.updateContact(contact.toEntity())
            return Result.success(Unit)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}