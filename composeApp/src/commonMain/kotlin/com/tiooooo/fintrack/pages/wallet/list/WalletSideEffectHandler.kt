package com.tiooooo.fintrack.pages.wallet.list

import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.local.entity.toWalletItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

suspend fun handleWalletIntentSideEffect(
    intent: WalletIntent,
    walletRepository: WalletRepository,
    setState: ((WalletState) -> WalletState) -> Unit,
    sendEffect: suspend (WalletEffect) -> Unit,
    currentState: () -> WalletState,
    screenModelScope: CoroutineScope,

    ) {
    when (intent) {
        is WalletIntent.LoadData -> {
            screenModelScope.launch {
                walletRepository.getAllWallets().collectLatest { data ->
                    setState {
                        currentState().copy(
                            wallets = data.map { it.toWalletItem() },
                            isLoading = false,
                        )
                    }
                }
            }

            screenModelScope.launch {
                walletRepository.getTotalAmount().collectLatest { total ->
                    setState {
                        currentState().copy(
                            totalAmount = total
                        )
                    }
                }
            }
        }

        is WalletIntent.OnWalletClicked -> {
            sendEffect(WalletEffect.NavigateToDetailWallet(intent.id))
        }

        is WalletIntent.OnAddWalletClicked -> {
            sendEffect(WalletEffect.NavigateToAddWallet)
        }

        else -> Unit
    }
}

