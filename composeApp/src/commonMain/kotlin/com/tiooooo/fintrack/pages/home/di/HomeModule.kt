package com.tiooooo.fintrack.pages.home.di

import com.tiooooo.fintrack.pages.home.HomeScreenModel
import com.tiooooo.fintrack.feature.settings.pages.settings.SettingScreenModel
import com.tiooooo.fintrack.pages.splash.SplashScreenModel
import org.koin.dsl.module

val homeModule = module {
  factory { HomeScreenModel(get(), get()) }
  factory { SplashScreenModel(get(), get()) }
}
