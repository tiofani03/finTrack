package com.tiooooo.fintrack.pages.home.di

import com.tiooooo.fintrack.pages.home.HomeScreenModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeScreenModel(get()) }
}
