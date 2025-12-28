package com.tiooooo.fintrack.auth.pages.login

sealed interface LoginEffect {
    data class OnNavigate(val route: String) : LoginEffect
}

sealed interface LoginIntent {
    fun reduce(state: LoginState): LoginState = state
    data class SetLoading(val isLoading: Boolean) : LoginIntent
    data class SetEmail(val email: String) : LoginIntent {
        override fun reduce(state: LoginState): LoginState {
            val isButtonEnable = email.isNotBlank() && state.password.isNotBlank()
            return state.copy(email = email, isButtonEnable = isButtonEnable)
        }
    }

    data class SetPassword(val password: String) : LoginIntent {
        override fun reduce(state: LoginState): LoginState {
            val isButtonEnable = state.email.isNotBlank() && password.isNotBlank()
            return state.copy(password = password, isButtonEnable = isButtonEnable)
        }
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isButtonEnable: Boolean = false,
)

data class LoginActions(
    val onNavigate: (String) -> Unit = {},
    val onEmailChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {},
)