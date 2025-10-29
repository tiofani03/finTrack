package com.tiooooo.fintrack.pages.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletScreenModel
import org.koin.compose.getKoin
import org.koin.core.qualifier.named

object DashboardRoute : Screen {
    @Composable
    override fun Content() {
        val dashboardScreenModel = rememberScreenModel { DashboardScreenModel() }
        val koin = getKoin()
        val walletScope = remember {
            koin.getScopeOrNull("wallet-scope") ?: koin.createScope("wallet-scope", named("wallet-scope"))
        }
        val walletScreenModel = walletScope.get<WalletScreenModel>()


        DashboardScreen(
            modifier = Modifier.fillMaxSize(),
            screenModel = dashboardScreenModel,
            homeScreenModel = koinScreenModel(),
            walletScreenModel = walletScreenModel,
            transactionScreenModel = koinScreenModel(),
            settingScreenModel = koinScreenModel(),
        )
    }
}