package com.tiooooo.fintrack.pages.splash

sealed interface SplashEffect {
    data object NavigateToOnboard : SplashEffect
    data object NavigateToDashboard : SplashEffect
}

sealed interface SplashIntent {
    data object CheckLoggedIn : SplashIntent
}

data class SplashState(
    val isLoading: Boolean = false
)