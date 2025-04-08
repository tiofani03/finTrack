package com.tiooooo.fintrack.pages.home

import androidx.compose.foundation.lazy.LazyListState
import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import com.tiooooo.fintrack.data.wallet.api.WalletRepository
import com.tiooooo.fintrack.pages.home.components.SummaryItem
import com.tiooooo.fintrack.pages.transaction.components.TransactionItem
import com.tiooooo.fintrack.pages.wallet.components.WalletItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val walletRepository: WalletRepository,
) : ScreenModel, ScrollStateManager {
    private val _lazyListState = MutableStateFlow(LazyListState())
    override val lazyListState: StateFlow<LazyListState> = _lazyListState

    private val _walletList = MutableStateFlow<List<WalletItem>>(emptyList())
    val walletList: StateFlow<List<WalletItem>> = _walletList

    private val _walletAmountTotal = MutableStateFlow<Double>(0.0)
    val walletAmountTotal: StateFlow<Double> = _walletAmountTotal

    private val _summaryList = MutableStateFlow<List<SummaryItem>>(emptyList())
    val summaryList: StateFlow<List<SummaryItem>> = _summaryList

    private val _summaryListState = MutableStateFlow(LazyListState())
    val summaryListState: StateFlow<LazyListState> = _summaryListState

    private val _latestTransactionList = MutableStateFlow<List<TransactionItem>>(emptyList())
    val latestTransactionList: StateFlow<List<TransactionItem>> = _latestTransactionList

    override fun updateState(state: LazyListState) {
        _lazyListState.value = state
    }

    fun updateSummaryList(state: LazyListState) {
        _summaryListState.value = state
    }

    init {
        fetchWalletData()
    }

    private fun fetchWalletData() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _walletList.value = walletRepository.getWalletItems()
            _walletAmountTotal.value = _walletList.value.sumOf { it.amountDouble }
            _summaryList.value = walletRepository.getSummaryItems()
            _latestTransactionList.value = walletRepository.getLatestTransaction()
        }
    }
}