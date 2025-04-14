package com.tiooooo.fintrack.di

import com.tiooooo.fintrack.data.wallet.api.WalletRepository
import com.tiooooo.fintrack.data.wallet.impl.DummyWalletRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WalletRepository> { DummyWalletRepository() }
}