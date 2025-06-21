package com.tiooooo.fintrack

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import com.tiooooo.fintrack.component.navigation.PlatformNavigatorContent
import com.tiooooo.fintrack.component.theme.FinTrackTheme
import com.tiooooo.fintrack.component.theme.SetupStatusBar
import com.tiooooo.fintrack.component.theme.rememberAppTheme
import com.tiooooo.fintrack.data.impl.DatastoreRepository
import com.tiooooo.fintrack.helper.ProvideGoogleAuthHelper
import com.tiooooo.fintrack.navigation.rememberRouteResolver
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@Composable
@Preview
fun App() {
  val appDatastore = koinInject<DatastoreRepository>()
  val darkTheme = rememberAppTheme(appDatastore)
  val routeResolver = rememberRouteResolver()

  SetupStatusBar(darkTheme)
  ProvideGoogleAuthHelper {
    FinTrackTheme(darkTheme) {
      Navigator(
        screen = routeResolver.require("/auth/login"),
        disposeBehavior = NavigatorDisposeBehavior(),
        onBackPressed = { true },
      ) { nav ->
        PlatformNavigatorContent(nav)
      }
    }
  }
}