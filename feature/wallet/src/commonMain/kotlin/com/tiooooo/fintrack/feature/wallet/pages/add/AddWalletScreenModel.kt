package com.tiooooo.fintrack.feature.wallet.pages.add

import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.data.api.CommonRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.wallet.api.model.Wallet
import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletEffect.AddWallet
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.Initial
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletAdded
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletAmountChanged
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletColorChanged
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletNameChanged
import kotlinx.coroutines.flow.MutableSharedFlow

class AddWalletScreenModel(
    private val walletRepository: WalletFirestoreRepository,
    private val commonRepository: CommonRepository,
    private val walletRefreshFlow: MutableSharedFlow<Unit>,
) : BaseScreenModel<AddWalletState, AddWalletIntent, AddWalletEffect>(
    AddWalletState()
) {
    init {
        dispatch(Initial)
    }

    fun reducer(state: AddWalletState, intent: AddWalletIntent): AddWalletState {
        return addWalletReducer(state, intent)
    }

    private fun addWalletReducer(state: AddWalletState, intent: AddWalletIntent): AddWalletState {
        return when (intent) {
            is OnWalletNameChanged -> state.copy(walletName = intent.name)
            is OnWalletAmountChanged -> state.copy(walletAmount = intent.amount)
            is OnWalletColorChanged -> state.copy(walletColor = intent.color)
            else -> state
        }
    }

    override suspend fun handleIntent(intent: AddWalletIntent) {
        when (intent) {
            is OnWalletAdded -> {
                val wallet = intent.wallet
                walletRepository.createWallet(
                    wallet = Wallet(
                        name = wallet.name,
                        balance = wallet.balance,
                        color = wallet.color,
                    ),
                    onSuccess = {
                        walletRefreshFlow.tryEmit(Unit)
                    },
                    onError = { errorMessage ->

                    }
                )
                sendEffect(AddWallet)
            }

            is Initial -> {
                val colorOptions = commonRepository.getAllColors()
                setState { state -> state.copy(colorOptions = colorOptions) }
            }

            else -> setState { reducer(it, intent) }
        }
    }
}