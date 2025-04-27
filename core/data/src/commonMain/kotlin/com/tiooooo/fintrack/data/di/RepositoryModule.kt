package com.tiooooo.fintrack.data.di

import com.tiooooo.fintrack.data.api.CategoryRepository
import com.tiooooo.fintrack.data.api.CommonRepository
import com.tiooooo.fintrack.data.api.TransactionRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.impl.CategoryRepositoryImpl
import com.tiooooo.fintrack.data.impl.CommonRepositoryImpl
import com.tiooooo.fintrack.data.impl.TransactionRepositoryImpl
import com.tiooooo.fintrack.data.impl.WalletRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<WalletRepository> { WalletRepositoryImpl(get()) }
    single<CommonRepository> { CommonRepositoryImpl() }
}