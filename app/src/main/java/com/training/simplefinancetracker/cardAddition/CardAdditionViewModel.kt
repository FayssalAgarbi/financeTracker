package com.training.simplefinancetracker.cardAddition

import com.airbnb.mvrx.*
import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.hiltMavericksViewModelFactory
import com.training.simplefinancetracker.persistence.CardRepository
import com.training.simplefinancetracker.persistence.CostType
import com.training.simplefinancetracker.persistence.Expenditure
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.*


data class CardAdditionState(
    val parentId: UUID,
    val isDone: Async<Boolean> = Uninitialized
) : MavericksState {
    constructor(parentId: String) : this(UUID.fromString(parentId))
}

class CardAdditionViewModel @AssistedInject constructor(
    @Assisted private val state: CardAdditionState,
    private val repository: CardRepository
) : BaseMvRxViewModel<CardAdditionState>(state) {

    fun insertCard(label: String, costType: CostType, cost: Double, isPaid: Boolean) =
        repository.insertCard(
            Expenditure(
                label = label,
                costType = costType,
                cost = cost,
                isPaid = isPaid,
                parentId = state.parentId
            )
        ).doOnComplete {
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