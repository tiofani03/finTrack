package com.tiooooo.fintrack.feature.wallet.pages.di

import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletScreenModel
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletScreenModel
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.dsl.module

val walletModule = module {
    single { MutableSharedFlow<Unit>(extraBufferCapacity = 1) }
    single { WalletScreenModel(get(), get()) }
    factory { AddWalletScreenModel(get(), get() , get()) }
}