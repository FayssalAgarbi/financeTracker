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
    val cardList: Async<List<Expenditure>> = Uninitialized
) : MavericksState

class CardListViewModel @AssistedInject constructor(
    @Assisted state: CardListState,
    private val repository: CardRepository
) : BaseMvRxViewModel<CardListState>(state) {

    init {
        repository.getCardsById(UUID(0, 0)).execute { copy(cardList = it) }
    }

    fun deleteCard(expenditure: Expenditure) = repository.deleteCard(expenditure).subscribe()

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<CardListViewModel, CardListState> {
        override fun create(state: CardListState): CardListViewModel
    }

    companion object :
        MavericksViewModelFactory<CardListViewModel, CardListState> by hiltMavericksViewModelFactory()
}