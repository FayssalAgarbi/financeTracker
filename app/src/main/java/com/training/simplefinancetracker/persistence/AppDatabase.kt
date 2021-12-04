package com.training.simplefinancetracker.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Category::class, Expenditure::class], version = 1)
@TypeConverters(UuidTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenditureDao(): ExpenditureDao

    companion object {
        const val DATABASE_NAME = "financeTracker"
    }
}