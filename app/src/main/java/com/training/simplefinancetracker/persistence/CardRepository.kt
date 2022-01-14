package com.training.simplefinancetracker.persistence

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


interface CardRepository {

    fun getCardsById(id: UUID): Observable<List<Expenditure>>

    fun insertCard(expenditure: Expenditure): Completable

    fun deleteCard(expenditure: Expenditure): Completable
}

class CardRepositoryImpl @Inject constructor(
    private val expenditureDao: ExpenditureDao
) : CardRepository {

    override fun getCardsById(id: UUID): Observable<List<Expenditure>> =
        expenditureDao.selectExpenditures(id)
            .subscribeOn(Schedulers.io())

    override fun insertCard(expenditure: Expenditure): Completable =
        expenditureDao.insertExpenditure(expenditure)
            .subscribeOn(Schedulers.io())

    override fun deleteCard(expenditure: Expenditure): Completable =
        expenditureDao.deleteExpenditure(expenditure)
            .concatWith {
                //This is likely to be a bad solution, I need to find a better way to
                //chain a single to a completable
                var delRows = 0
                expenditureDao.deleteLooseEnds()
                    .doAfterSuccess {
                        delRows = it
                    }.repeat()
                    .takeUntil { delRows == 0}
                    .subscribe()
            }
            .subscribeOn(Schedulers.io())
}