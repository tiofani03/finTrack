package com.tiooooo.fintrack.feature.dashboard.navigation

import com.tiooooo.fintrack.feature.dashboard.DashboardScreen
import com.tiooooo.fintrack.feature.dashboard.SampleDashboardScreen
import com.tiooooo.fintrack.feature.dashboard.SampleHome
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import com.tiooooo.fintrack.navigation.Route
import com.tiooooo.fintrack.navigation.RouteRegistry

object DashboardFeature : FeatureModule {
  override fun registerRoutes() {
    RouteRegistry.register(
      Route("/dashboard") { SampleDashboardScreen() }
    )
    RouteRegistry.register(
      Route("/main") { DashboardScreen() }
    )
    RouteRegistry.register(
      Route("/home") { SampleHome() }
    )
  }
}