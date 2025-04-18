package com.tiooooo.fintrack.pages.wallet.list

fun walletReducer(
    state: WalletState,
    intent: WalletIntent,
): WalletState {
    return when(intent){
        is WalletIntent.LoadData -> state
        is WalletIntent.UpdateListState -> state.copy(listState = intent.state)
        is WalletIntent.OnAddWalletClicked -> state
        is WalletIntent.OnWalletClicked -> state
    }
}