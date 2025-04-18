package com.tiooooo.fintrack.pages.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.tiooooo.fintrack.pages.home.HomeScreenModel
import com.tiooooo.fintrack.pages.settings.SettingScreenModel
import com.tiooooo.fintrack.pages.transaction.TransactionScreenModel
import com.tiooooo.fintrack.pages.wallet.list.WalletScreenModel

object DashboardRoute : Screen {
    @Composable
    override fun Content() {
        val dashboardScreenModel = rememberScreenModel { DashboardScreenModel() }
        val homeScreenModel = koinScreenModel<HomeScreenModel>()
        val walletScreenModel = koinScreenModel<WalletScreenModel>()
        val transactionScreenModel = rememberScreenModel { TransactionScreenModel() }
        val settingScreenModel = rememberScreenModel { SettingScreenModel() }

        DashboardScreen(
            modifier = Modifier.fillMaxSize(),
            screenModel = dashboardScreenModel,
            homeScreenModel = homeScreenModel,
            walletScreenModel = walletScreenModel,
            transactionScreenModel = transactionScreenModel,
            settingScreenModel = settingScreenModel,
        )
    }
}