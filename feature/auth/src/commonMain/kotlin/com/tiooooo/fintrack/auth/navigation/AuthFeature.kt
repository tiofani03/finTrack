package com.tiooooo.fintrack.auth.navigation

import com.tiooooo.fintrack.auth.SampleAuthScreen
import com.tiooooo.fintrack.auth.SampleProfile
import com.tiooooo.fintrack.auth.SampleRegisterScreen
import com.tiooooo.fintrack.auth.SampleSearchScreen
import com.tiooooo.fintrack.navigation.helper.FeatureModule
import com.tiooooo.fintrack.navigation.Route
import com.tiooooo.fintrack.navigation.RouteRegistry
import com.tiooooo.fintrack.navigation.parseRoute

object AuthFeature : FeatureModule {
  override fun registerRoutes() {
    RouteRegistry.register(
      Route("/auth/login") { SampleAuthScreen() }
    )
    RouteRegistry.register(
      Route("/auth/register/{id}") { path ->
        val args = parseRoute(path, "/auth/register/{id}")
        val id = args.pathParams["id"] ?: ""
        val ref = args.queryParams["ref"]
        SampleRegisterScreen(id, ref)
      }
    )
    RouteRegistry.register(
      Route("/search") { SampleSearchScreen() }
    )
    RouteRegistry.register(
      Route("/profile") { SampleProfile() }
    )
  }
}