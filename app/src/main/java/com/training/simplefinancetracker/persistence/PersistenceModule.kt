package com.training.simplefinancetracker.persistence

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PersistenceModule {

    @Binds
    @Singleton
    abstract fun cardRepository(repository: CardRepositoryImpl): CardRepository

    companion object {

        @Provides
        @Singleton
        fun appDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        @Singleton
        fun expenditureDao(database: AppDatabase): ExpenditureDao = database.expenditureDao()
    }
}