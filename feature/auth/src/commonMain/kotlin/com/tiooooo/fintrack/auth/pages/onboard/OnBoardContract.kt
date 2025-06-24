package com.tiooooo.fintrack.auth.pages.onboard

sealed interface OnBoardEffect {
  data object NavigateToDashboard : OnBoardEffect
  data class ShowToast(val message: String) : OnBoardEffect
}

sealed interface OnBoardIntent {
  data class ShowToast(val message: String) : OnBoardIntent
  data class ShowSuccessToast(val message: String) : OnBoardIntent
  data object NavigateToDashboard : OnBoardIntent
  data class SetLoading(val isLoading: Boolean) : OnBoardIntent
}

data class OnboardState(
  val isLoading: Boolean = false
)