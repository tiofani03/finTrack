package com.tiooooo.fintrack.auth.di

import com.tiooooo.fintrack.auth.navigation.AuthNavigation
import com.tiooooo.fintrack.auth.pages.onboard.OnboardScreenModel
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {
  factory<FeatureModule>(named("auth-nav")) { AuthNavigation }
  factory { OnboardScreenModel(get()) }
}