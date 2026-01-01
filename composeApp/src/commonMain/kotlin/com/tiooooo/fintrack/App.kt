package com.tiooooo.fintrack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.tiooooo.fintrack.component.navigation.PlatformNavigatorContent
import com.tiooooo.fintrack.component.theme.SetupStatusBar
import com.tiooooo.fintrack.component.theme.ThemeTransitionHost
import com.tiooooo.fintrack.component.theme.rememberAppTheme
import com.tiooooo.fintrack.data.impl.DatastoreRepository
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
  ThemeTransitionHost(darkTheme) {
    var appReady by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
      GoogleAuthProvider.create(
        credentials = GoogleAuthCredentials(
          serverId = getPlatform().serverId
        )
      )
      appReady = true
    }
    AnimatedVisibility(
      modifier = Modifier.fillMaxSize(),
      visible = appReady
    ) {
      Navigator(
        screen = routeResolver.require("/splash"),
        disposeBehavior = NavigatorDisposeBehavior(),
        onBackPressed = { true },
      ) { nav ->
        PlatformNavigatorContent(nav)
      }
    }
  }
}