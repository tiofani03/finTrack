package com.tiooooo.fintrack.pages.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.tiooooo.fintrack.pages.home.HomeScreenModel
import com.tiooooo.fintrack.pages.settings.SettingScreenModel
import com.tiooooo.fintrack.pages.transaction.TransactionScreenModel

object DashboardRoute : Screen {
    @Composable
    override fun Content() {
        val dashboardScreenModel = rememberScreenModel { DashboardScreenModel() }
        val homeScreenModel = rememberScreenModel { HomeScreenModel() }
        val transactionScreenModel = rememberScreenModel { TransactionScreenModel() }
        val settingScreenModel = rememberScreenModel { SettingScreenModel() }

        DashboardScreen(
            modifier = Modifier.fillMaxSize(),
            screenModel = dashboardScreenModel,
            homeScreenModel = homeScreenModel,
            transactionScreenModel = transactionScreenModel,
            settingScreenModel = settingScreenModel,
        )
    }
}