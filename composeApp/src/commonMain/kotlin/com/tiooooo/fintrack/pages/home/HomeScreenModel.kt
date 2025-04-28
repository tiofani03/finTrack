package com.tiooooo.fintrack.pages.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.data.api.WalletRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val walletRepository: WalletRepository,
) : BaseScreenModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        dispatch(HomeIntent.LoadData)
    }

    override fun reducer(state: HomeState, intent: HomeIntent): HomeState {
        return when (intent) {
            is HomeIntent.UpdateListState -> state.copy(listState = intent.state)
            is HomeIntent.UpdateSummaryState -> state.copy(summaryListState = intent.state)
            else -> state
        }
    }


    override suspend fun handleIntentSideEffect(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadData -> {
                setState { it.copy(isLoading = true) }
                screenModelScope.launch {
                    walletRepository.getTotalAmount().collectLatest { total ->
                        setState { it.copy(totalAmount = total) }
                    }
                }
            }

            is HomeIntent.OnTransactionClicked -> {
                screenModelScope.launch {
                    sendEffect(HomeEffect.NavigateToTransaction)
                }
            }

            else -> Unit
        }
    }
}
