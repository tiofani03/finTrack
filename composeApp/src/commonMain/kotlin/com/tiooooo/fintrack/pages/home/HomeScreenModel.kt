package com.tiooooo.fintrack.pages.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.BaseScreenModel
import com.tiooooo.fintrack.data.wallet.api.WalletRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val walletRepository: WalletRepository,
) : BaseScreenModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        dispatch(HomeIntent.LoadData)
    }

    override fun dispatch(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadData -> {
                setState { it.copy(isLoading = true) }
                screenModelScope.launch {
                    delay(1000)

                    val wallets = walletRepository.getWalletItems()
                    val total = wallets.sumOf { it.amountDouble }
                    val summary = walletRepository.getSummaryItems()
                    val transactions = walletRepository.getLatestTransaction()

                    setState {
                        it.copy(
                            wallets = wallets,
                            totalAmount = total,
                            summary = summary,
                            transactions = transactions,
                            isLoading = false
                        )
                    }
                }
            }

            is HomeIntent.UpdateListState -> {
                setState { it.copy(listState = intent.state) }
            }

            is HomeIntent.UpdateSummaryState -> {
                setState { it.copy(summaryListState = intent.state) }
            }
            is HomeIntent.OnTransactionClicked -> {
                screenModelScope.launch {
                    sendEffect(HomeEffect.NavigateToTransaction)
                }
            }
        }
    }
}
