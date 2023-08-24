package com.example.baseandroid.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseandroid.data.model.ItemFile

@Database(entities = [ItemFile::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun documentReaderDao(): DocumentReaderDao
}