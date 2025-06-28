package com.tiooooo.fintrack.pages.di

import com.tiooooo.fintrack.navigation.helper.FeatureModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainNavigationModule = module {
  factory<FeatureModule>(named("main-nav")) { MainFeature }
}