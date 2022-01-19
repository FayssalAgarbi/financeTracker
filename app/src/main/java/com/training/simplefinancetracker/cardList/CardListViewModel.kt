package com.training.simplefinancetracker.cardList

import com.airbnb.mvrx.*
import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.hiltMavericksViewModelFactory
import com.training.simplefinancetracker.persistence.CardRepository
import com.training.simplefinancetracker.persistence.Expenditure
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.disposables.Disposable
import java.util.*


data class CardListState(
    val parentId: UUID,
    val cardList: Async<List<Expenditure>> = Uninitialized,
    val totalCost: Async<Pair<Int, Int>> = Uninitialized
) : MavericksState {
    constructor(parentId: String) : this(UUID.fromString(parentId), Uninitialized, Uninitialized)
}

class CardListViewModel @AssistedInject constructor(
    @Assisted private val state: CardListState,
    private val repository: CardRepository
) : BaseMvRxViewModel<CardListState>(state) {

    val id
        get() = state.parentId

    init {
        repository.getCardsById(state.parentId).execute { copy(cardList = it) }
        repository.calculateCardCostById(state.parentId).execute { copy(totalCost = it) }
    }

    fun updateParentCost(): Disposable =
        repository.updateParentCostById(state.parentId)
            .subscribe()

    fun deleteCard(expenditure: Expenditure): Disposable =
        repository.deleteCard(expenditure)
            .subscribe()

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<CardListViewModel, CardListState> {
        override fun create(state: CardListState): CardListViewModel
    }

    companion object :
        MavericksViewModelFactory<CardListViewModel, CardListState> by hiltMavericksViewModelFactory()
}