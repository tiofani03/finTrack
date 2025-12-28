package com.tiooooo.fintrack.auth.pages.login

sealed interface LoginEffect {
    data class OnNavigate(val route: String) : LoginEffect
}

sealed interface LoginIntent {
    fun reduce(state: LoginState): LoginState = state
    data class SetLoading(val isLoading: Boolean) : LoginIntent
}

data class LoginState(
    val isLoading: Boolean = false,
)