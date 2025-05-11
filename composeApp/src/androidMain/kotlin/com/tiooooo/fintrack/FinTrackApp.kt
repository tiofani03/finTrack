package com.tiooooo.fintrack

import android.app.Application
import com.google.firebase.FirebaseApp
import com.tiooooo.fintrack.adaptor.AndroidAuthService
import com.tiooooo.fintrack.di.initKoin
import org.koin.android.ext.koin.androidContext

class FinTrackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        multiplatform.network.cmptoast.AppContext.apply {
            set(applicationContext)
        }
        initKoin(
            authService = AndroidAuthService()
        ) {
            androidContext(this@FinTrackApp)
        }
    }
}