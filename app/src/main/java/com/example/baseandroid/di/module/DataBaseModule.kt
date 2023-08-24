package com.example.baseandroid.di.module

import android.app.Application
import androidx.room.Room
import com.example.baseandroid.data.db.AppDatabase
import com.example.baseandroid.data.db.DocumentReaderDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    internal fun bindDatabaseModule(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "documentreader.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun bindDocumentReaderDao(appDatabase: AppDatabase): DocumentReaderDao = appDatabase.documentReaderDao()

}