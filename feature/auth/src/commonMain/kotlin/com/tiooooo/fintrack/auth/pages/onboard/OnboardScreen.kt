package com.tiooooo.fintrack.auth.pages.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

object OnboardScreen : Screen {
  @Composable
  override fun Content() {
    val onboardScreenModel = rememberScreenModel { OnboardScreenModel() }
    OnboardContent(
      modifier = Modifier.fillMaxSize(),
      onboardScreenModel = onboardScreenModel,
    )
  }
}