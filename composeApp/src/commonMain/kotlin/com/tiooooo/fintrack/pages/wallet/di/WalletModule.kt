package com.tiooooo.fintrack.pages.wallet.di

import com.tiooooo.fintrack.pages.home.HomeScreenModel
import com.tiooooo.fintrack.pages.wallet.WalletScreenModel
import org.koin.dsl.module

val walletModule = module {
    factory { WalletScreenModel(get()) }
}