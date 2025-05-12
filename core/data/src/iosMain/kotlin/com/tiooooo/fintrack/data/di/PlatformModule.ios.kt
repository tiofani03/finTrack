package com.tiooooo.fintrack.data.di

import androidx.room.RoomDatabase
import org.koin.dsl.module
import com.tiooooo.fintrack.data.local.AppDatabase
import com.tiooooo.fintrack.data.local.datastore.dataStore
import com.tiooooo.fintrack.data.local.getDatabaseBuilder
import com.tiooooo.fintrack.data.utils.GoogleAuthHelper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind

actual fun platformModule() = module {
    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
    single { dataStore() }
    factoryOf(::GoogleAuthHelper) bind GoogleAuthHelper::class
}