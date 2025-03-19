package com.tiooooo.fintrack

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import com.tiooooo.fintrack.component.navigation.PlatformNavigatorContent
import com.tiooooo.fintrack.component.theme.FinTrackTheme
import com.tiooooo.fintrack.pages.splash.SplashRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    FinTrackTheme {
        Navigator(
            screen = SplashRoute,
            disposeBehavior = NavigatorDisposeBehavior(),
            onBackPressed = { true },
        ) { navigator ->
            PlatformNavigatorContent(navigator)
        }
    }
}