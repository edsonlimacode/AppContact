package com.edsonlimadev.appcontact.di

import android.content.Context
import androidx.room.Room
import com.edsonlimadev.appcontact.data.dao.ContactDao
import com.edsonlimadev.appcontact.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "appcontact.db"
        ).build()
    }

    @Provides
    fun provideContatoDao(appDatabase: AppDatabase): ContactDao {
        return appDatabase.contactDao()
    }
}