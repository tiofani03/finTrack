package com.tiooooo.fintrack.pages.wallet.list

import androidx.compose.foundation.lazy.grid.LazyGridState
import com.tiooooo.fintrack.data.model.wallet.WalletItem

sealed interface WalletEffect {
    data object NavigateToAddWallet : WalletEffect
    data class NavigateToDetailWallet(val walletId: Int) : WalletEffect
}

sealed interface WalletIntent {
    data object LoadData : WalletIntent
    data class UpdateListState(val state: LazyGridState) : WalletIntent
    data object OnAddWalletClicked : WalletIntent
    data class OnWalletClicked(val id: Int) : WalletIntent
}

data class WalletState(
    val wallets: List<WalletItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val listState: LazyGridState = LazyGridState(),
    val isLoading: Boolean = false
)