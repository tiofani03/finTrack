package com.tiooooo.fintrack.pages.di

import com.tiooooo.fintrack.feature.wallet.pages.di.walletModule
import com.tiooooo.fintrack.pages.home.di.homeModule
import com.tiooooo.fintrack.pages.transaction.di.transactionModule

val pagesModule = listOf(
    homeModule,
    walletModule,
    transactionModule,
)