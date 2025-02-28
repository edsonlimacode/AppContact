package com.edsonlimadev.appcontact.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.edsonlimadev.appcontact.data.entities.ContactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun findById(id: Long): ContactEntity

    @Update
    suspend fun update(contactEntity: ContactEntity)

    @Delete
    suspend fun delete(contactEntity: ContactEntity)

    @Query("UPDATE contacts SET favorite = :isFavorite WHERE id = :id")
    suspend fun addOrRemoveFavorite(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM contacts WHERE favorite = 1")
    fun getFavorites(): Flow<List<ContactEntity>?>

}