package com.tiooooo.fintrack.pages.wallet.add

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class AddWalletRoute : Screen {

    @Composable
    override fun Content() {
        AddWalletScreen(
            modifier = Modifier.fillMaxSize(),
            screenModel = koinScreenModel()
        )
    }
}