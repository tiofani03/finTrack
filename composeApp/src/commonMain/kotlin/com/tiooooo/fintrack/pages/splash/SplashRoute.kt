package com.tiooooo.fintrack.pages.splash

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.tiooooo.fintrack.component.theme.primaryDark
import com.tiooooo.fintrack.component.theme.primaryLight

object SplashRoute : Screen {

    @Composable
    override fun Content() {
        val splashModifier =
            Modifier.background(Brush.verticalGradient(listOf(primaryDark, primaryLight)))
        SplashScreen(
            modifier = splashModifier,
            splashScreenModel = koinScreenModel(),
        )
    }

}