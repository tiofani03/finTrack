package com.tiooooo.fintrack.auth.di

import com.tiooooo.fintrack.auth.navigation.AuthFeature
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureAuthModule = module {
  factory<FeatureModule>(named("auth-nav")) { AuthFeature }
}