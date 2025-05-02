package com.tiooooo.fintrack.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithWallet(
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "walletId",
        entityColumn = "id"
    )
    val wallet: WalletEntity
)
