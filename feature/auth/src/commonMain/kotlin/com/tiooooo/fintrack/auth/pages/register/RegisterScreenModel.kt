package com.tiooooo.fintrack.auth.pages.register

import com.tiooooo.fintrack.component.base.BaseScreenModel

class RegisterScreenModel : BaseScreenModel<RegisterState, RegisterIntent, RegisterEffect>(
    RegisterState()
) {

    override suspend fun handleIntent(intent: RegisterIntent) {
        setState { intent.reduce(state.value) }
    }
}