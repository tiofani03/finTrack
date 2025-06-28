package com.tiooooo.fintrack.di

import com.tiooooo.fintrack.auth.di.authModule
import com.tiooooo.fintrack.data.user.di.dataUserModule
import com.tiooooo.fintrack.data.wallet.di.dataWalletModule
import com.tiooooo.fintrack.feature.dashboard.di.DashboardModule
import com.tiooooo.fintrack.navigation.RouteResolver
import com.tiooooo.fintrack.pages.di.mainNavigationModule
import org.koin.dsl.module

val featureModules = listOf(
  mainNavigationModule,
  DashboardModule,
  authModule
)

val navigationModule = module {
  single { RouteResolver() }
}

val dataModules = listOf(
  dataUserModule,
  dataWalletModule,
)

