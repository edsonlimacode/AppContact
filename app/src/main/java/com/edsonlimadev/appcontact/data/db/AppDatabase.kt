package com.edsonlimadev.appcontact.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edsonlimadev.appcontact.data.dao.ContactDao
import com.edsonlimadev.appcontact.data.entities.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}