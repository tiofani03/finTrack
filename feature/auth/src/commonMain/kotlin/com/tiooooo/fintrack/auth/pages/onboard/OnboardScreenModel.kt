package com.tiooooo.fintrack.auth.pages.onboard

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.component.resources.IconHelper
import com.tiooooo.fintrack.data.user.api.repo.UserRepository
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class OnboardScreenModel(
  private val userRepository: UserRepository,
) :
  BaseScreenModel<OnboardState, OnBoardIntent, OnBoardEffect>(OnboardState()) {

  override suspend fun handleIntent(intent: OnBoardIntent) {
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

      is OnBoardIntent.RegisterSuccess -> registerUser(intent.user)
      is OnBoardIntent.NavigateToDashboard -> sendEffect(OnBoardEffect.NavigateToDashboard)
      else -> setState { intent.reduce(state.value) }
    }
  }

  private fun registerUser(user: FirebaseUser?) {
    screenModelScope.launch {
      userRepository.createUser(
        user = user,
        onSuccess = {
          launch {
            sendEffect(OnBoardEffect.ShowToast("User registered successfully."))
            sendEffect(OnBoardEffect.NavigateToDashboard)
          }
        },
        onError = { message ->
          launch {
            if (message == "already exists") {
              sendEffect(OnBoardEffect.NavigateToDashboard)
            } else {
              sendEffect(OnBoardEffect.ShowToast("Failed to register user: $message"))
            }
          }
        }
      )
    }
  }
}