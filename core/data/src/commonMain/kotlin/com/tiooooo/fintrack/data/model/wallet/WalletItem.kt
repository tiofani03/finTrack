package com.tiooooo.fintrack.data.model.wallet

import androidx.compose.ui.graphics.Color
import com.tiooooo.fintrack.data.utils.formatToReadableString
import com.tiooooo.fintrack.data.utils.timeNow
import fintrack.core.data.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import fintrack.core.data.generated.resources.ic_default_wallet

data class WalletItem(
    val id: Int,
    val name: String,
    val amount: String,
    val amountDouble: Double,
    val color: Color,
    val image: DrawableResource,
    val updatedAt: String = timeNow().formatToReadableString(),
)

fun createDefaultWalletItem(): WalletItem {
    return WalletItem(
        id = 0,
        name = "Dompet",
        amount = "Rp 0",
        amountDouble = 0.0,
        color = Color(0xFF3F51B5),
        image = Res.drawable.ic_default_wallet,
    )
}