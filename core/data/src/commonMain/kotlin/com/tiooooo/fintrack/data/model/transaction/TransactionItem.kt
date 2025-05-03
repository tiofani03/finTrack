package com.tiooooo.fintrack.data.model.transaction

import kotlinx.datetime.LocalDateTime

data class TransactionItem(
    val id: Long,
    val name: String,
    val amount: Double,
    val type: TransactionType,
    val walletId: Long,
    val walletName: String,
    val categoryId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val updatedAtAsString: String,
)