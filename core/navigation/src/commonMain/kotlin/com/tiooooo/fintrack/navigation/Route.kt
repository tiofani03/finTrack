package com.tiooooo.fintrack.navigation

import cafe.adriel.voyager.core.screen.Screen

typealias ScreenFactory = (String) -> Screen

data class Route(
  val pattern: String,
  val factory: ScreenFactory
)

data class RouteArgs(
  val pathParams: Map<String, String>,
  val queryParams: Map<String, String>
)

fun parseRoute(path: String, routePattern: String): RouteArgs {
  val fullPath = path.substringBefore("?")
  val fullQuery = path.substringAfter("?", "")

  val pathSegments = fullPath.trim('/').split('/')
  val patternSegments = routePattern.trim('/').split('/')

  val pathParams = mutableMapOf<String, String>()
  patternSegments.zip(pathSegments).forEach { (pattern, actual) ->
    if (pattern.startsWith("{") && pattern.endsWith("}")) {
      val key = pattern.removePrefix("{").removeSuffix("}")
      pathParams[key] = actual
    }
  }

  val queryParams = fullQuery
    .split("&")
    .mapNotNull {
      val (key, value) = it.split("=")
        .takeIf { it.size == 2 } ?: return@mapNotNull null
      key to value
    }.toMap()

  return RouteArgs(pathParams, queryParams)
}
