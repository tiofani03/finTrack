package com.tiooooo.fintrack.data.di

import com.tiooooo.fintrack.data.local.AppDatabase
import com.tiooooo.fintrack.data.local.getRoomDatabase
import org.koin.dsl.module

val localModule = module {
    single { getRoomDatabase(get()) }
    single { get<AppDatabase>().walletDao() }
    single { get<AppDatabase>().categoryDao() }
    single { get<AppDatabase>().transactionDao() }
    single { get<AppDatabase>().transactionWalletDao() }
}