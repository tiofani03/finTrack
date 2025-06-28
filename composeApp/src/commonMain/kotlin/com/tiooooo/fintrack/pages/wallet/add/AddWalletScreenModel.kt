package com.tiooooo.fintrack.pages.wallet.add

import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.data.api.CommonRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.wallet.api.model.Wallet
import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository

class AddWalletScreenModel(
    private val walletRepository: WalletFirestoreRepository,
    private val commonRepository: CommonRepository,
) : BaseScreenModel<AddWalletState, AddWalletIntent, AddWalletEffect>(
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
                walletRepository.createWallet(
                    wallet = Wallet(
                        name = wallet.name,
                        balance = wallet.balance,
                        color = wallet.color,
                    ),
                    onSuccess = {

                    },
                    onError = { errorMessage ->

                    }
                )
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