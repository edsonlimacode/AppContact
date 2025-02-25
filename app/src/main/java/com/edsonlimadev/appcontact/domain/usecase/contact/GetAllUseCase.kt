package com.edsonlimadev.appcontact.domain.usecase.contact

import com.edsonlimadev.appcontact.data.repositories.ContactRepository
import com.edsonlimadev.appcontact.domain.mapper.toDomain
import com.edsonlimadev.appcontact.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    operator fun invoke(): Result<Flow<List<Contact>?>> {
        try {
            val contacts = contactRepository.getAllContacts().map { it?.map { it.toDomain() } }
            return Result.success(contacts)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }
}