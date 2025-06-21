package com.tiooooo.fintrack.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.tiooooo.fintrack.navigation.pages.DefaultNotFoundScreen

class RouteResolver {
  fun resolve(path: String): Screen? {
    val pathOnly = path.substringBefore("?").trim('/')
    val parts = pathOnly.split('/')

    val matchedRoute = RouteRegistry.all().firstOrNull { route ->
      val routeParts = route.pattern.trim('/').split('/')
      routeParts.size == parts.size && routeParts.zip(parts).all { (rp, p) ->
        rp.startsWith("{") || rp == p
      }
    }
    return matchedRoute?.factory?.invoke(path)
  }

  fun require(path: String): Screen {
    return resolve(path) ?: DefaultNotFoundScreen()
  }
}