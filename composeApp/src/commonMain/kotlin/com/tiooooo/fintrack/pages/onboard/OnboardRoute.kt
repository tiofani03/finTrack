package com.tiooooo.fintrack.pages.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

object OnboardRoute : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { OnboardScreenModel() }
        OnboardScreen(
            modifier = Modifier.fillMaxSize(),
            onboardScreenModel = screenModel,
        )
    }
}