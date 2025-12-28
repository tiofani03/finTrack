package com.tiooooo.fintrack.auth.pages.login

import com.tiooooo.fintrack.component.base.BaseScreenModel

class LoginScreenModel : BaseScreenModel<LoginState, LoginIntent, LoginEffect>(LoginState()) {

    override suspend fun handleIntent(intent: LoginIntent) {
        setState { intent.reduce(state.value) }
    }
}