package com.tiooooo.fintrack.pages.transaction.add

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class AddTransactionRoute : Screen {

    @Composable
    override fun Content() {
        AddTransactionScreen(
            modifier = Modifier.fillMaxSize(),
            screenModel = koinScreenModel()
        )
    }
}