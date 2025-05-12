package com.tiooooo.fintrack.di

import com.tiooooo.fintrack.data.di.localModule
import com.tiooooo.fintrack.data.di.platformModule
import com.tiooooo.fintrack.data.di.repositoryModule
import com.tiooooo.fintrack.pages.di.pagesModule
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        localModule,
        repositoryModule,
        dispatcherModule,
        *pagesModule.toTypedArray()
    )
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

fun initKoin() = initKoin {}