package com.tiooooo.fintrack.pages.di

import com.tiooooo.fintrack.navigation.Route
import com.tiooooo.fintrack.navigation.RouteRegistry
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import com.tiooooo.fintrack.pages.dashboard.DashboardRoute
import com.tiooooo.fintrack.pages.home.HomeScreen
import com.tiooooo.fintrack.pages.splash.SplashRoute

object MainFeature : FeatureModule {
  override fun registerRoutes() {
    RouteRegistry.register(Route("/splash") { SplashRoute })
    RouteRegistry.register(Route("/dashboard") { DashboardRoute })
  }
}