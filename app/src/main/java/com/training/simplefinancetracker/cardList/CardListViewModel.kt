package com.training.simplefinancetracker.cardList

import com.airbnb.mvrx.*
import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.hiltMavericksViewModelFactory
import com.training.simplefinancetracker.persistence.CardRepository
import com.training.simplefinancetracker.persistence.Expenditure
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.*


data class CardListState(
    val parentId: UUID,
    val cardList: Async<List<Expenditure>> = Uninitialized
) : MavericksState {
    constructor(parentId: String) : this(UUID.fromString(parentId), Uninitialized)
}

class CardListViewModel @AssistedInject constructor(
    @Assisted private val state: CardListState,
    private val repository: CardRepository
) : BaseMvRxViewModel<CardListState>(state) {

    val id = state.parentId

    init {
        repository.getCardsById(state.parentId).execute { copy(cardList = it) }
    }

    //TODO Cascade the deletion to child items.
    fun deleteCard(expenditure: Expenditure) = repository.deleteCard(expenditure).subscribe()

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<CardListViewModel, CardListState> {
        override fun create(state: CardListState): CardListViewModel
    }

    companion object :
        MavericksViewModelFactory<CardListViewModel, CardListState> by hiltMavericksViewModelFactory()
}