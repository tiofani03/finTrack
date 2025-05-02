package com.tiooooo.fintrack.pages.transaction.list

import androidx.compose.foundation.lazy.LazyListState
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import com.tiooooo.fintrack.data.api.TransactionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionListScreenModel(
    private val transactionRepository: TransactionRepository,
) : BaseScreenModel<TransactionListState, TransactionListIntent, TransactionListEffect>(
    TransactionListState()
), ScrollStateManager {

    init {
        dispatch(TransactionListIntent.LoadData)
    }

    private val _lazyListState = MutableStateFlow(LazyListState())
    override val lazyListState: StateFlow<LazyListState> = _lazyListState

    override fun updateState(state: LazyListState) {
        _lazyListState.value = state
    }

    fun refreshData(onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dispatch(TransactionListIntent.LoadData)
            onComplete()
        }
    }

    override fun reducer(
        state: TransactionListState,
        intent: TransactionListIntent
    ): TransactionListState {
        return state
    }

    override suspend fun handleIntentSideEffect(intent: TransactionListIntent) {
        when(intent){
            is TransactionListIntent.LoadData -> {
                transactionRepository.getAllTransactions().collect { transactions ->
                    setState { it.copy(transactions = transactions) }
                }
            }
        }
    }
}