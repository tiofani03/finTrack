package com.tiooooo.fintrack.data.wallet.di

import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository
import com.tiooooo.fintrack.data.wallet.impl.WalletFirestoreRepositoryImpl
import org.koin.dsl.module

val dataWalletModule = module {
  single<WalletFirestoreRepository> { WalletFirestoreRepositoryImpl() }
}