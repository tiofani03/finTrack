package com.tiooooo.fintrack.data.di

import com.tiooooo.fintrack.data.api.CommonRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.impl.CommonRepositoryImpl
import com.tiooooo.fintrack.data.impl.WalletRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WalletRepository> { WalletRepositoryImpl(get()) }
    single<CommonRepository> { CommonRepositoryImpl() }
}