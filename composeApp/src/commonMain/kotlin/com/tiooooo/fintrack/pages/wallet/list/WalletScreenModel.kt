package com.tiooooo.fintrack.pages.wallet.list

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.base.BaseScreenModelRedux
import com.tiooooo.fintrack.data.api.WalletRepository

class WalletScreenModel(
    private val walletRepository: WalletRepository,
) : BaseScreenModelRedux<WalletState, WalletIntent, WalletEffect>(WalletState()) {

    init {
        dispatch(WalletIntent.LoadData)
    }

    override fun reducer(state: WalletState, intent: WalletIntent): WalletState {
        return walletReducer(state, intent)
    }

    override suspend fun handleIntentSideEffect(intent: WalletIntent) {
        handleWalletIntentSideEffect(
            intent = intent,
            walletRepository = walletRepository,
            setState = ::setState,
            sendEffect = { sendEffect(it) },
            currentState = { state.value },
            screenModelScope = screenModelScope,
        )
    }
}