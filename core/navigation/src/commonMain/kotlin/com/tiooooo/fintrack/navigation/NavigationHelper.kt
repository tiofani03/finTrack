package com.tiooooo.fintrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.navigation.pages.DefaultNotFoundScreen
import org.koin.mp.KoinPlatform.getKoin

class NavigationHelper(
  private val resolver: RouteResolver,
  private val navigator: Navigator
) {
  fun navigate(path: String) {
    resolver.resolve(path)?.let { screen ->
      navigator.push(screen)
    } ?: navigator.push(DefaultNotFoundScreen())
  }
  fun resolve(path: String): Screen = resolver.require(path)
}

@Composable
fun rememberNavHelper(): NavigationHelper {
  val resolver = getKoin().get<RouteResolver>()
  val navigator = LocalNavigator.currentOrThrow
  return remember { NavigationHelper(resolver, navigator) }
}

@Composable
fun rememberRouteResolver(): RouteResolver {
  return remember { getKoin().get<RouteResolver>() }
}
