package com.batuhan.cryptocrazy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CryptoCrazy : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}