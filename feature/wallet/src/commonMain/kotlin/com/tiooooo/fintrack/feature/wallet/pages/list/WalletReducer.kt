package com.tiooooo.fintrack.feature.wallet.pages.list

import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.LoadData
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.OnAddWalletClicked
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.OnWalletClicked
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.UpdateListState

fun walletReducer(
    state: WalletState,
    intent: WalletIntent,
): WalletState {
    return when(intent){
        is LoadData -> state
        is UpdateListState -> state.copy(listState = intent.state)
        is OnAddWalletClicked -> state
        is OnWalletClicked -> state
    }
}