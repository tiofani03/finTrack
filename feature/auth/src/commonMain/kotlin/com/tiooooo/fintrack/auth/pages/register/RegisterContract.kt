package com.tiooooo.fintrack.auth.pages.register

sealed interface RegisterEffect {
    data class OnNavigate(val route: String) : RegisterEffect
}

sealed interface RegisterIntent {
    fun reduce(state: RegisterState): RegisterState = state
    data class SetLoading(val isLoading: Boolean) : RegisterIntent
    data class SetName(val name: String) : RegisterIntent {
        override fun reduce(state: RegisterState): RegisterState {
            val newFormState = state.registerFormState.copy(name = name)
            return state.copy(
                registerFormState = newFormState,
                isButtonEnable = newFormState.validate()
            )
        }
    }

    data class SetEmail(val email: String) : RegisterIntent {
        override fun reduce(state: RegisterState): RegisterState {
            val newFormState = state.registerFormState.copy(email = email)
            return state.copy(
                registerFormState = newFormState,
                isButtonEnable = newFormState.validate()
            )
        }
    }

    data class SetPassword(val password: String) : RegisterIntent {
        override fun reduce(state: RegisterState): RegisterState {
            val newFormState = state.registerFormState.copy(password = password)
            return state.copy(
                registerFormState = newFormState,
                isButtonEnable = newFormState.validate()
            )
        }
    }

    data class SetConfirmPassword(val confirmPassword: String) : RegisterIntent {
        override fun reduce(state: RegisterState): RegisterState {
            val newFormState = state.registerFormState.copy(confirmPassword = confirmPassword)
            return state.copy(
                registerFormState = newFormState,
                isButtonEnable = newFormState.validate()
            )
        }
    }
}

data class RegisterState(
    val isLoading: Boolean = false,
    val registerFormState: RegisterFormState = RegisterFormState(),
    val isButtonEnable: Boolean = false,
)

data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
)

fun RegisterFormState.validate(): Boolean {
    return name.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank() &&
            password == confirmPassword
}

data class RegisterActions(
    val onNavigate: (String) -> Unit = {},
    val onNameChanged: (String) -> Unit = {},
    val onEmailChanged: (String) -> Unit = {},
    val onPasswordChanged: (String) -> Unit = {},
    val onConfirmPasswordChanged: (String) -> Unit = {},
)