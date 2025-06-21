package com.tiooooo.fintrack.di

import com.tiooooo.fintrack.auth.di.featureAuthModule
import com.tiooooo.fintrack.feature.dashboard.di.featureDashboardModule
import com.tiooooo.fintrack.navigation.RouteResolver
import org.koin.dsl.module

val featureModules = listOf(
  featureDashboardModule,
  featureAuthModule
)

val navigationModule = module {
  single { RouteResolver() }
}

