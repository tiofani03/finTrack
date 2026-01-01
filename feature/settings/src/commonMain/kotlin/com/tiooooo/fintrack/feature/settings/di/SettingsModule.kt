package com.tiooooo.fintrack.feature.settings.di

import com.tiooooo.fintrack.feature.settings.pages.settings.SettingScreenModel
import org.koin.dsl.module

val settingsModule = module {
    factory { SettingScreenModel(get()) }
}