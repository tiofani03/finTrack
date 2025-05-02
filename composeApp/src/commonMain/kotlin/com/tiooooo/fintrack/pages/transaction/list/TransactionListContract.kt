package com.tiooooo.fintrack.pages.transaction.list

import androidx.compose.foundation.lazy.grid.LazyGridState
import com.tiooooo.fintrack.data.model.transaction.TransactionItem

sealed interface TransactionListEffect {}

sealed interface TransactionListIntent {
    data object LoadData : TransactionListIntent
}

data class TransactionListState(
    val transactions: List<TransactionItem> = emptyList(),
    val listState: LazyGridState = LazyGridState(),
    val isLoading: Boolean = false
)