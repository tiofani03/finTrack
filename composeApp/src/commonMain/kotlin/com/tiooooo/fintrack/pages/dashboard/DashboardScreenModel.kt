package com.tiooooo.fintrack.pages.dashboard

import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.component.bottomNavigation.BottomNavModel
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_menu_home_not_selected
import fintrack.composeapp.generated.resources.ic_menu_home_selected
import fintrack.composeapp.generated.resources.ic_menu_pocket_not_selected
import fintrack.composeapp.generated.resources.ic_menu_pocket_selected
import fintrack.composeapp.generated.resources.ic_menu_setting_not_selected
import fintrack.composeapp.generated.resources.ic_menu_setting_selected
import fintrack.composeapp.generated.resources.ic_menu_transaction_not_selected
import fintrack.composeapp.generated.resources.ic_menu_transaction_selected
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardScreenModel : ScreenModel {
    val bottomNavList = MutableStateFlow(
        listOf(
            BottomNavModel(
                iconNotSelected = Res.drawable.ic_menu_home_not_selected,
                iconSelected = Res.drawable.ic_menu_home_selected,
                label = "Home",
                slug = BottomNavTarget.HOME,
            ),
            BottomNavModel(
                iconNotSelected = Res.drawable.ic_menu_pocket_not_selected,
                iconSelected = Res.drawable.ic_menu_pocket_selected,
                label = "Dompet",
                slug = BottomNavTarget.WALLET,
            ),
            BottomNavModel(
                iconNotSelected = Res.drawable.ic_menu_transaction_not_selected,
                iconSelected = Res.drawable.ic_menu_transaction_selected,
                label = "Transaksi",
                slug = BottomNavTarget.TRANSACTIONS,
            ),
            BottomNavModel(
                iconNotSelected = Res.drawable.ic_menu_setting_not_selected,
                iconSelected = Res.drawable.ic_menu_setting_selected,
                label = "Settings",
                slug = BottomNavTarget.SETTINGS,
            ),
        )
    ).asStateFlow()

    private val _bottomSheetPosition = MutableStateFlow(BottomNavTarget.HOME)
    val bottomSheetPosition: StateFlow<String> = _bottomSheetPosition

    fun updateBottomSheetPosition(positionPath: String) {
        _bottomSheetPosition.value = positionPath
    }

}

object BottomNavTarget {
    const val HOME = "home"
    const val SETTINGS = "settings"
    const val WALLET = "wallet"
    const val TRANSACTIONS = "transactions"
}