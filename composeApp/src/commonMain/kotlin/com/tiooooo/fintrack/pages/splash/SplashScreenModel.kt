package com.tiooooo.fintrack.pages.splash

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.data.user.api.repo.UserRepository
import kotlinx.coroutines.launch

class SplashScreenModel(
  private val userRepository: UserRepository,
) : BaseScreenModel<SplashState, SplashIntent, SplashEffect>(SplashState()) {

  override fun reducer(state: SplashState, intent: SplashIntent): SplashState {
    return state
  }

  override suspend fun handleIntentSideEffect(intent: SplashIntent) {
    when (intent) {
      is SplashIntent.CheckLoggedIn -> checkUser()
    }
  }

  private fun checkUser() {
    screenModelScope.launch {
      val userId = userRepository.getCurrentUserId()
      if (userId.isNullOrEmpty()) {
        sendEffect(SplashEffect.NavigateToOnboard)
      } else {
        sendEffect(SplashEffect.NavigateToDashboard)
      }
    }
  }
}