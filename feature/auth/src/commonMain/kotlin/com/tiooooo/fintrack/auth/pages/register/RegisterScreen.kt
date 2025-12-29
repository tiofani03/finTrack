package com.tiooooo.fintrack.auth.pages.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.tiooooo.fintrack.auth.pages.register.components.RegisterContent

object RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val registerScreenModel = koinScreenModel<RegisterScreenModel>()
        RegisterContent(
            modifier = Modifier.fillMaxSize(),
            registerScreenModel = registerScreenModel,
        )
    }
}