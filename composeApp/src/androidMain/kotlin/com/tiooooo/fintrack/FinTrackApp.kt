package com.tiooooo.fintrack

import android.app.Application
import com.tiooooo.fintrack.di.initKoin
import org.koin.android.ext.koin.androidContext

class FinTrackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        multiplatform.network.cmptoast.AppContext.apply {
            set(applicationContext)
        }
        initKoin{
            androidContext(this@FinTrackApp)
        }
    }
}