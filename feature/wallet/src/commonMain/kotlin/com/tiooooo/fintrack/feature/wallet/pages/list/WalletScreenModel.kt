package com.tiooooo.fintrack.feature.wallet.pages.list

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.LoadData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class WalletScreenModel(
    private val walletRepository: WalletFirestoreRepository,
    private val walletRefreshFlow: MutableSharedFlow<Unit>,
) : BaseScreenModel<WalletState, WalletIntent, WalletEffect>(WalletState()) {

    init {
        dispatch(LoadData)
        screenModelScope.launch {
            walletRefreshFlow.collect {
                dispatch(LoadData)
            }
        }
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