package com.tiooooo.fintrack.data.local.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tiooooo.fintrack.data.model.wallet.WalletItem
import com.tiooooo.fintrack.data.utils.formatToReadableString
import com.tiooooo.fintrack.data.utils.timeNow
import fintrack.core.data.generated.resources.Res
import fintrack.core.data.generated.resources.ic_default_wallet
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val balance: Double,
    val currency: String,
    val color: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime = timeNow(),
)

fun WalletEntity?.toWalletItem() = WalletItem(
    id = "",
    name = this?.name ?: "Dompet",
    amount = this?.balance.toString(),
    amountDouble = this?.balance ?: 0.0,
    color = Color(this?.color ?: 0xFFFFFFFF.toInt()),
    image = Res.drawable.ic_default_wallet,
    updatedAt = this?.updatedAt?.formatToReadableString().orEmpty(),
)