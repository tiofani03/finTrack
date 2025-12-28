package com.tiooooo.fintrack.auth.pages.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.tiooooo.fintrack.auth.pages.login.components.LoginContent

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val loginScreenModel = koinScreenModel<LoginScreenModel>()
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            loginScreenModel = loginScreenModel,
        )
    }
}