package com.training.simplefinancetracker.core

import com.training.simplefinancetracker.di.AssistedViewModelFactory
import com.training.simplefinancetracker.di.MavericksViewModelComponent
import com.training.simplefinancetracker.di.ViewModelKey
import com.training.simplefinancetracker.mainMenu.MainMenuViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel::class)
    fun mainMenuViewModel(factory: MainMenuViewModel.Factory): AssistedViewModelFactory<*, *>
}