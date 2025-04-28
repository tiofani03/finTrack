package com.tiooooo.fintrack.data.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import com.tiooooo.fintrack.data.local.AppDatabase
import com.tiooooo.fintrack.data.local.datastore.dataStore
import com.tiooooo.fintrack.data.local.getDatabaseBuilder

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
    single { dataStore() }
}