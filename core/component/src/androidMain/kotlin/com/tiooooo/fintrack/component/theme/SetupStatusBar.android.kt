package com.tiooooo.fintrack.component.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

@Composable
actual fun SetupStatusBar(darkTheme: Boolean) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window

    SideEffect {
        window?.statusBarColor = Color.Transparent.toArgb()
        window?.navigationBarColor = if (darkTheme) {
            Color.Black.toArgb()
        } else {
            Color.White.toArgb()
        }

        ViewCompat.getWindowInsetsController(view)?.let { controller ->
            controller.isAppearanceLightStatusBars = !darkTheme
            controller.isAppearanceLightNavigationBars = !darkTheme
        }
    }
}
