package com.training.simplefinancetracker.cardList

import com.airbnb.mvrx.*
import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


data class CardListState(
    val placeholder: Async<Unit> = Uninitialized
) : MavericksState

class CardListViewModel @AssistedInject constructor(
    @Assisted state: CardListState
) : BaseMvRxViewModel<CardListState>(state) {

    init {

    }

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<CardListViewModel, CardListState> {
        override fun create(state: CardListState): CardListViewModel
    }

    companion object :
        MavericksViewModelFactory<CardListViewModel, CardListState> by hiltMavericksViewModelFactory()
}