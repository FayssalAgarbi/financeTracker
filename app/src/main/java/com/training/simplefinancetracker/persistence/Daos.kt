package com.training.simplefinancetracker.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

@Dao
interface ExpenditureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenditure(expenditure: Expenditure): Completable

    @Query("SELECT * FROM Expenditure WHERE parentId = :expenditureId")
    fun selectExpenditures(expenditureId: UUID): Observable<List<Expenditure>>

    @Delete
    fun deleteExpenditure(expenditure: Expenditure): Completable
}