package com.tiooooo.fintrack.pages.transaction.di

import com.tiooooo.fintrack.pages.transaction.add.AddTransactionScreenModel
import com.tiooooo.fintrack.pages.transaction.list.TransactionListScreenModel
import org.koin.dsl.module

val transactionModule = module {
    factory { AddTransactionScreenModel(get(), get(), get()) }
    factory { TransactionListScreenModel(get()) }
}