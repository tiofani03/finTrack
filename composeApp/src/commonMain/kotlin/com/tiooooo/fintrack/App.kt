package com.tiooooo.fintrack

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import com.tiooooo.fintrack.component.navigation.PlatformNavigatorContent
import com.tiooooo.fintrack.component.theme.FinTrackTheme
import com.tiooooo.fintrack.component.theme.SetupStatusBar
import com.tiooooo.fintrack.component.theme.rememberAppTheme
import com.tiooooo.fintrack.data.impl.DatastoreRepository
import com.tiooooo.fintrack.pages.splash.SplashRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@Composable
@Preview
fun App() {
    val appDatastore = koinInject<DatastoreRepository>()
    val darkTheme = rememberAppTheme(appDatastore)
    SetupStatusBar(darkTheme)
    FinTrackTheme(darkTheme) {
        Navigator(
            screen = SplashRoute,
            disposeBehavior = NavigatorDisposeBehavior(),
            onBackPressed = { true },
        ) { navigator ->
            PlatformNavigatorContent(navigator)
        }
    }
}