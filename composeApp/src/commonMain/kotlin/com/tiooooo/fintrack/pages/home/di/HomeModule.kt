package com.tiooooo.fintrack.pages.home.di

import com.tiooooo.fintrack.pages.home.HomeScreenModel
import com.tiooooo.fintrack.pages.settings.SettingScreenModel
import com.tiooooo.fintrack.pages.splash.SplashScreenModel
import org.koin.dsl.module

val homeModule = module {
  factory { HomeScreenModel(get(), get()) }
  factory { SettingScreenModel(get()) }
  factory { SplashScreenModel(get(), get()) }
}
