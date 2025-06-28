package com.tiooooo.fintrack.auth.pages.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

object OnboardScreen : Screen {
  @Composable
  override fun Content() {
    val onboardScreenModel = koinScreenModel<OnboardScreenModel>()
    OnboardContent(
      modifier = Modifier.fillMaxSize(),
      onboardScreenModel = onboardScreenModel,
    )
  }
}