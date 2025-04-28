package com.tiooooo.fintrack.pages.transaction.add

import com.tiooooo.fintrack.data.model.category.CategoryItem
import com.tiooooo.fintrack.data.model.transaction.TransactionItem
import com.tiooooo.fintrack.data.model.transaction.TransactionType
import com.tiooooo.fintrack.data.model.wallet.WalletItem
import com.tiooooo.fintrack.data.utils.timeNow
import kotlinx.datetime.LocalDateTime

sealed interface AddTransactionEffect {
    data object AddTransaction : AddTransactionEffect
}

sealed interface AddTransactionIntent {
    data object Initial : AddTransactionIntent
    data class OnTransactionNameChanged(val name: String) : AddTransactionIntent
    data class OnTransactionAmountChanged(val amount: String) : AddTransactionIntent
    data class OnTransactionDateChanged(val date: LocalDateTime) : AddTransactionIntent
    data class OnTransactionTypeChanged(val type: TransactionType) : AddTransactionIntent
    data class OnCategoryChanged(val category: CategoryItem) : AddTransactionIntent
    data class OnWalletChanged(val walletId: Long) : AddTransactionIntent

    data class OnTransactionAdded(val transaction: TransactionItem) : AddTransactionIntent
}

data class AddTransactionState(
    val transactionName: String = "",
    val transactionAmount: String = "",
    val transactionDate: LocalDateTime = timeNow(),
    val transactionType: TransactionType = TransactionType.EXPENSE,
    val selectedCategoryId: Long? = null,
    val selectedWalletId: Long? = null,
    val categories: List<CategoryItem> = listOf(),
    val wallets: List<WalletItem> = listOf(),
)