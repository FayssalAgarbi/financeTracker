package com.training.simplefinancetracker

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        Timber.plant(Timber.DebugTree())
    }
}