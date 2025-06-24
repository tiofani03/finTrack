package com.tiooooo.fintrack.auth.pages.onboard

import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.component.resources.IconHelper

class OnboardScreenModel :
  BaseScreenModel<OnboardState, OnBoardIntent, OnBoardEffect>(OnboardState()) {
  val listOfImages = listOf(
    IconHelper.icOnBoardSort,
    IconHelper.icOnboardData,
    IconHelper.icOnboardHappy
  )

  val listOfText = listOf(
    "Ini teks pertama",
    "Ini teks kedua",
    "Ini teks ketiga",
  )

  override fun reducer(state: OnboardState, intent: OnBoardIntent): OnboardState {
    return when (intent) {
      is OnBoardIntent.SetLoading -> state.copy(isLoading = intent.isLoading)
      else -> state
    }
  }

  override suspend fun handleIntentSideEffect(intent: OnBoardIntent) {
    when (intent) {
      is OnBoardIntent.ShowToast -> {
        val message = if (intent.message.contains("A network error")) {
          "Internet connection error, please check your connection and try again."
        } else if (intent.message.contains("Idtoken is null")) {
          "Sign in canceled."
        } else {
          "Unexpected error: ${intent.message}"
        }
        sendEffect(OnBoardEffect.ShowToast(message))
      }
      is OnBoardIntent.ShowSuccessToast -> sendEffect(OnBoardEffect.ShowToast(intent.message))
      is OnBoardIntent.NavigateToDashboard -> sendEffect(OnBoardEffect.NavigateToDashboard)
      else -> Unit
    }
  }
}