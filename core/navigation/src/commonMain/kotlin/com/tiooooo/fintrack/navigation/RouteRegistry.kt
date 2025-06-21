package com.tiooooo.fintrack.navigation

object RouteRegistry {
  private val routes = mutableListOf<Route>()

  fun register(route: Route) {
    routes.add(route)
  }

  fun all(): List<Route> = routes.toList()
}