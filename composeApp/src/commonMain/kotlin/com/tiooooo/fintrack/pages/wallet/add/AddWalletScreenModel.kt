package com.tiooooo.fintrack.pages.wallet.add

import com.tiooooo.fintrack.base.BaseScreenModelRedux
import com.tiooooo.fintrack.data.api.CommonRepository
import com.tiooooo.fintrack.data.api.WalletRepository

class AddWalletScreenModel(
    private val walletRepository: WalletRepository,
    private val commonRepository: CommonRepository,
) : BaseScreenModelRedux<AddWalletState, AddWalletIntent, AddWalletEffect>(
    AddWalletState()
) {
    init {
        dispatch(AddWalletIntent.Initial)
    }

    override fun reducer(state: AddWalletState, intent: AddWalletIntent): AddWalletState {
        return addWalletReducer(state, intent)
    }

    private fun addWalletReducer(state: AddWalletState, intent: AddWalletIntent): AddWalletState {
        return when (intent) {
            is AddWalletIntent.OnWalletNameChanged -> state.copy(walletName = intent.name)
            is AddWalletIntent.OnWalletAmountChanged -> state.copy(walletAmount = intent.amount)
            is AddWalletIntent.OnWalletColorChanged -> state.copy(walletColor = intent.color)
            else -> state
        }
    }

    override suspend fun handleIntentSideEffect(intent: AddWalletIntent) {
        when (intent) {
            is AddWalletIntent.OnWalletAdded -> {
                val wallet = intent.wallet
                walletRepository.insertWallet(wallet)
                sendEffect(AddWalletEffect.AddWallet)
            }

            is AddWalletIntent.Initial -> {
                val colorOptions = commonRepository.getAllColors()
                setState { state -> state.copy(colorOptions = colorOptions) }
            }

            else -> Unit
        }
    }
}