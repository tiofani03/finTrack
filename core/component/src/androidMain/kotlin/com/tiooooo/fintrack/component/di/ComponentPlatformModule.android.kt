package com.tiooooo.fintrack.component.di

import com.tiooooo.fintrack.component.utils.GoogleAuthUiProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val componentPlatformModule: Module =
    module {
        factoryOf(::GoogleAuthUiProvider) bind GoogleAuthUiProvider::class
    }