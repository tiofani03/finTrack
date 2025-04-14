package com.tiooooo.fintrack.pages.wallet

import com.tiooooo.fintrack.data.wallet.api.WalletRepository

suspend fun handleWalletIntentSideEffect(
    intent: WalletIntent,
    walletRepository: WalletRepository,
    setState: ((WalletState) -> WalletState) -> Unit,
    sendEffect: suspend (WalletEffect) -> Unit,
    currentState: () -> WalletState
) {
    when (intent) {
        is WalletIntent.LoadData -> {
            val wallets = walletRepository.getWalletItems()
            val total = wallets.sumOf { it.amountDouble }
            setState {
                currentState().copy(
                    wallets = wallets,
                    totalAmount = total,
                    isLoading = false
                )
            }
        }

        is WalletIntent.UpdateAmount -> {
            val wallets = walletRepository.getWalletItems()
            val total = wallets.sumOf { it.amountDouble }
            setState {
                currentState().copy(
                    totalAmount = total
                )
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

