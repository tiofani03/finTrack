package com.tiooooo.fintrack.data.di

import com.tiooooo.fintrack.data.api.CategoryRepository
import com.tiooooo.fintrack.data.api.CommonRepository
import com.tiooooo.fintrack.data.api.RemoteConfigRepository
import com.tiooooo.fintrack.data.impl.DatastoreRepository
import com.tiooooo.fintrack.data.api.TransactionRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.impl.CategoryRepositoryImpl
import com.tiooooo.fintrack.data.impl.CommonRepositoryImpl
import com.tiooooo.fintrack.data.impl.RemoteConfigRepositoryImpl
import com.tiooooo.fintrack.data.impl.TransactionRepositoryImpl
import com.tiooooo.fintrack.data.impl.WalletRepositoryImpl
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfig
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module
import kotlin.time.Duration

val repositoryModule = module {
    single<FirebaseRemoteConfig> {
        runBlocking {
            Firebase.remoteConfig.apply {
                settings {
                    minimumFetchInterval = Duration.ZERO
                }
            }
        }
    }
    single { DatastoreRepository(get()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get(), get(), get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<WalletRepository> { WalletRepositoryImpl(get()) }
    single<CommonRepository> { CommonRepositoryImpl() }
    single { DatastoreRepository(get()) }
    single<RemoteConfigRepository> { RemoteConfigRepositoryImpl(get(), get()) }
}