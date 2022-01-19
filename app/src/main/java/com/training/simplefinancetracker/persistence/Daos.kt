package com.training.simplefinancetracker.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

@Dao
interface ExpenditureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpenditure(expenditure: Expenditure): Completable

    @Query("SELECT * FROM Expenditure WHERE parentId = :expenditureId")
    fun selectExpenditures(expenditureId: UUID): Observable<List<Expenditure>>

    @Query("SELECT SUM(cost) a FROM Expenditure WHERE parentId = :parentId")
    fun calculateTotalCardCost(parentId: UUID): Observable<Int?>

    @Query("SELECT SUM(cost) a FROM Expenditure WHERE parentId = :parentId AND isPaid = 0")
    fun calculateUnpaidCardCost(parentId: UUID): Observable<Int?>

    @Query("UPDATE Expenditure set cost = (SELECT SUM(cost) FROM EXPENDITURE WHERE parentId = :parentId) WHERE id = :parentId")
    fun updateParentCost(parentId: UUID): Completable

    @Delete
    fun deleteExpenditure(expenditure: Expenditure): Completable

    @Query("DELETE FROM Expenditure WHERE parentId <> '00000000-0000-0000-0000-000000000000' AND parentId not in (SELECT id FROM EXPENDITURE)")
    fun deleteLooseEnds(): Single<Int>
}