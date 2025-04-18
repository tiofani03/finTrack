package com.tiooooo.fintrack.pages.wallet.add

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.tiooooo.fintrack.data.local.entity.WalletEntity
import com.tiooooo.fintrack.data.utils.timeNow

sealed interface AddWalletEffect {
    data object AddWallet : AddWalletEffect
}

sealed interface AddWalletIntent {
    data object Initial : AddWalletIntent
    data class OnWalletNameChanged(val name: String) : AddWalletIntent
    data class OnWalletAmountChanged(val amount: String) : AddWalletIntent
    data class OnWalletColorChanged(val color: Color) : AddWalletIntent
    data class OnWalletAdded(val wallet: WalletEntity) : AddWalletIntent
}

data class AddWalletState(
    val walletName: String = "",
    val walletColor: Color = Color.White,
    val walletCurrency: String = "",
    val walletAmount: String = "",
    val colorOptions: List<Color> = listOf(),
    val wallet: WalletEntity = WalletEntity(
        name = "Dompet",
        balance = 0.0,
        currency = "0",
        color = Color.White.toArgb(),
        createdAt = timeNow(),
        updatedAt = timeNow(),
    )
)