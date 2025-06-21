package com.tiooooo.fintrack.feature.dashboard.di

import com.tiooooo.fintrack.feature.dashboard.navigation.DashboardFeature
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureDashboardModule = module {
  factory<FeatureModule>(named("dashboard-nav")) { DashboardFeature }
}