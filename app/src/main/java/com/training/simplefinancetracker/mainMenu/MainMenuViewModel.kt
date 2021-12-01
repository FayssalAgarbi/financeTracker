package com.training.simplefinancetracker.mainMenu

import com.airbnb.mvrx.*
import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


data class MainMenuState(
    val placeholder: Async<Unit> = Uninitialized
) : MavericksState

class MainMenuViewModel @AssistedInject constructor(
    @Assisted state: MainMenuState
) : BaseMvRxViewModel<MainMenuState>(state) {

    init {

    }

    @AssistedFactory
    interface Factory :
        AssistedViewModelFactory<MainMenuViewModel, MainMenuState> {
        override fun create(state: MainMenuState): MainMenuViewModel
    }

    companion object :
            MavericksViewModelFactory<MainMenuViewModel, MainMenuState> by hiltMavericksViewModelFactory()
}