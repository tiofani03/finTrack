package com.tiooooo.fintrack.auth.navigation

import com.tiooooo.fintrack.auth.SampleRegisterScreen
import com.tiooooo.fintrack.auth.pages.onboard.OnboardScreen
import com.tiooooo.fintrack.navigation.Route
import com.tiooooo.fintrack.navigation.RouteRegistry
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import com.tiooooo.fintrack.navigation.parseRoute

object AuthNavigation : FeatureModule {
  override fun registerRoutes() {
    RouteRegistry.register(
      Route("/auth/onboard") { OnboardScreen }
    )
    RouteRegistry.register(
      Route("/auth/register/{id}") { path ->
        val args = parseRoute(path, "/auth/register/{id}")
        val id = args.pathParams["id"] ?: ""
        val ref = args.queryParams["ref"]
        SampleRegisterScreen(id, ref)
      }
    )
  }
}