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

      is OnBoardIntent.RegisterSuccess -> registerUser(intent.user)
      is OnBoardIntent.NavigateToDashboard -> sendEffect(OnBoardEffect.NavigateToDashboard)
      else -> Unit
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