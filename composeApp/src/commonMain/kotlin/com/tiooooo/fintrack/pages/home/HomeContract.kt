package com.tiooooo.fintrack.pages.home

import androidx.compose.foundation.lazy.LazyListState
import com.tiooooo.fintrack.data.model.wallet.WalletItem
import com.tiooooo.fintrack.pages.home.components.SummaryItem
import com.tiooooo.fintrack.pages.transaction.components.TransactionItem

sealed interface HomeEffect {
    data object NavigateToTransaction : HomeEffect
    data class ShowToast(val message: String) : HomeEffect
}

sealed interface HomeIntent {
    data object LoadData : HomeIntent
    data class UpdateListState(val state: LazyListState) : HomeIntent
    data class UpdateSummaryState(val state: LazyListState) : HomeIntent
    data object OnTransactionClicked : HomeIntent
}

data class HomeState(
    val wallets: List<WalletItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val summary: List<SummaryItem> = emptyList(),
    val transactions: List<TransactionItem> = emptyList(),
    val listState: LazyListState = LazyListState(),
    val summaryListState: LazyListState = LazyListState(),
    val isLoading: Boolean = false
)