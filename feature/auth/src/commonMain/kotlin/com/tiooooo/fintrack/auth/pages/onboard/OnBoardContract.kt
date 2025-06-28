package com.tiooooo.fintrack.auth.pages.onboard

import dev.gitlive.firebase.auth.FirebaseUser

sealed interface OnBoardEffect {
  data object NavigateToDashboard : OnBoardEffect
  data class ShowToast(val message: String) : OnBoardEffect
}

sealed interface OnBoardIntent {
  data class ShowToast(val message: String) : OnBoardIntent
  data class RegisterSuccess(val user: FirebaseUser?): OnBoardIntent
  data object NavigateToDashboard : OnBoardIntent
  data class SetLoading(val isLoading: Boolean) : OnBoardIntent
}

data class OnboardState(
  val isLoading: Boolean = false
)