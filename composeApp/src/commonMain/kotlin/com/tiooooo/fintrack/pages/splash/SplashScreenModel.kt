package com.tiooooo.fintrack.pages.splash

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.tiooooo.fintrack.pages.onboard.OnboardRoute

class SplashScreenModel(
    private val navigator: Navigator,
) : ScreenModel {
    val delayTime = 1000L

    fun navigateToOnboardPage() {
        navigator.replace(OnboardRoute)
    }
}