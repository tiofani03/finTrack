package com.tiooooo.fintrack.pages.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.BaseScreenModel
import com.tiooooo.fintrack.data.api.WalletRepository
import kotlinx.coroutines.flow.collectLatest
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
                    walletRepository.getTotalAmount().collectLatest { total ->
                        setState { it.copy(totalAmount = total) }
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
