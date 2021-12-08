package com.training.simplefinancetracker.cardAddition

import com.airbnb.mvrx.*
import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.hiltMavericksViewModelFactory
import com.training.simplefinancetracker.persistence.CardRepository
import com.training.simplefinancetracker.persistence.Expenditure
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


data class CardAdditionState(
    val isDone: Async<Boolean> = Uninitialized
) : MavericksState

class CardAdditionViewModel @AssistedInject constructor(
    @Assisted state: CardAdditionState,
    private val repository: CardRepository
) : BaseMvRxViewModel<CardAdditionState>(state) {

    fun insertCard(expenditure: Expenditure) = repository.insertCard(expenditure).doOnComplete {
        setState { copy(isDone = Success(true)) }
    }.subscribe()

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<CardAdditionViewModel, CardAdditionState> {
        override fun create(state: CardAdditionState): CardAdditionViewModel
    }

    companion object :
        MavericksViewModelFactory<CardAdditionViewModel, CardAdditionState> by hiltMavericksViewModelFactory()
}