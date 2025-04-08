package com.tiooooo.fintrack.data.wallet.api

import com.tiooooo.fintrack.pages.home.components.SummaryItem
import com.tiooooo.fintrack.pages.transaction.components.TransactionItem
import com.tiooooo.fintrack.pages.wallet.components.WalletItem

interface WalletRepository {
    suspend fun getWalletItems(): List<WalletItem>
    suspend fun getSummaryItems(): List<SummaryItem>
    suspend fun getLatestTransaction(): List<TransactionItem>
}
