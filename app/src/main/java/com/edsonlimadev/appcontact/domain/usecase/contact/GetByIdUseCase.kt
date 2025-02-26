package com.edsonlimadev.appcontact.domain.usecase.contact

import com.edsonlimadev.appcontact.data.repositories.ContactRepository
import com.edsonlimadev.appcontact.domain.mapper.toDomain
import com.edsonlimadev.appcontact.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetByIdUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(id: Long): Result<Contact> {
        try {
            val contact = contactRepository.getContactById(id).toDomain()
            return Result.success(contact)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}