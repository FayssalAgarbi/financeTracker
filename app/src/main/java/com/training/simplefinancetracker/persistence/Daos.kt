package com.training.simplefinancetracker.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

@Dao
interface ExpenditureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenditure(category: Category): Completable

    @Transaction
    @Query("SELECT * FROM Category")
    fun selectAllCategoriesWithExpenditures(): Observable<List<CategoryWithExpenditures>>

    @Query("SELECT * FROM Expenditure WHERE categoryId = :categoryId")
    fun selectExpenditures(categoryId: UUID): Observable<Expenditure>

}