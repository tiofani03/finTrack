package com.tiooooo.fintrack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tiooooo.fintrack.data.model.transaction.TransactionItem
import com.tiooooo.fintrack.data.model.transaction.TransactionType
import com.tiooooo.fintrack.data.utils.timeNow
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val amount: Double,
    val type: TransactionType,
    val walletId: Long,
    val categoryId: Long,
    val createdAt: LocalDateTime = timeNow(),
    val updatedAt: LocalDateTime = timeNow()
)

fun TransactionEntity.toItem(): TransactionItem {
    return TransactionItem(
        id = id,
        name = name,
        amount = amount,
        type = type,
        walletId = walletId,
        categoryId = categoryId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun TransactionItem.toEntity(): TransactionEntity = TransactionEntity(
    id = id,
    walletId = walletId,
    categoryId = categoryId,
    name = name,
    amount = amount,
    type = type,
    createdAt = createdAt
)