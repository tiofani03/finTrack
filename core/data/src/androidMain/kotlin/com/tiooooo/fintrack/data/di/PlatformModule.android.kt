package com.tiooooo.fintrack.data.di

import androidx.room.RoomDatabase
import com.tiooooo.fintrack.data.local.AppDatabase
import com.tiooooo.fintrack.data.local.datastore.dataStore
import com.tiooooo.fintrack.data.local.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder(get()) }
    single { dataStore(get()) }
}