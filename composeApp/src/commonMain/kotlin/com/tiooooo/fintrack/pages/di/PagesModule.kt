package com.tiooooo.fintrack.pages.di

import com.tiooooo.fintrack.pages.home.di.homeModule
import com.tiooooo.fintrack.pages.transaction.di.transactionModule
import com.tiooooo.fintrack.pages.wallet.di.walletModule

val pagesModule = listOf(
    homeModule,
    walletModule,
    transactionModule,
)