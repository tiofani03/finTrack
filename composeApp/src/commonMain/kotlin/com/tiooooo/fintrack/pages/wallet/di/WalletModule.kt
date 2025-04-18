package com.tiooooo.fintrack.pages.wallet.di

import com.tiooooo.fintrack.pages.wallet.add.AddWalletScreenModel
import com.tiooooo.fintrack.pages.wallet.list.WalletScreenModel
import org.koin.dsl.module

val walletModule = module {
    factory { WalletScreenModel(get()) }
    factory { AddWalletScreenModel(get(), get()) }
}