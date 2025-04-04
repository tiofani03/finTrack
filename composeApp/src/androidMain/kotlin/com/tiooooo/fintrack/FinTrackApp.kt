package com.tiooooo.fintrack

import android.app.Application

class FinTrackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        multiplatform.network.cmptoast.AppContext.apply {
            set(applicationContext)
        }
    }
}