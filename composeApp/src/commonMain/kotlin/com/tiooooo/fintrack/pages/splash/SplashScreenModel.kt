package com.tiooooo.fintrack.pages.splash

import com.tiooooo.fintrack.component.base.BaseScreenModel

class SplashScreenModel : BaseScreenModel<SplashState, SplashIntent, SplashEffect>(SplashState()) {

    override fun reducer(state: SplashState, intent: SplashIntent): SplashState {
        return state
    }

    override suspend fun handleIntentSideEffect(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.NavigateToOnboard -> {
                sendEffect(SplashEffect.NavigateToOnboard)
            }

            is SplashIntent.NavigateToDashboard -> {
                sendEffect(SplashEffect.NavigateToDashboard)
            }
        }
    }
}