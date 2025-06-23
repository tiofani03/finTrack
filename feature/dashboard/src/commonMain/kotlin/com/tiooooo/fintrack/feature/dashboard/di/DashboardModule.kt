package com.tiooooo.fintrack.feature.dashboard.di

import com.tiooooo.fintrack.feature.dashboard.navigation.DashboardNavigation
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DashboardModule = module {
  factory<FeatureModule>(named("dashboard-nav")) { DashboardNavigation }
}