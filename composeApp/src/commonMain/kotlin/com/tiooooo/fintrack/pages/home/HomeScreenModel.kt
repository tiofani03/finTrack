package com.tiooooo.fintrack.pages.home

import androidx.compose.foundation.lazy.LazyListState
import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import com.tiooooo.fintrack.data.wallet.api.WalletRepository
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

    private val _dashboardList = MutableStateFlow<List<String>>(emptyList())
    val dashboardList: StateFlow<List<String>> = _dashboardList

    private val _lazyListState = MutableStateFlow(LazyListState())
    override val lazyListState: StateFlow<LazyListState> = _lazyListState

    private val _walletList = MutableStateFlow<List<WalletItem>>(emptyList())
    val walletList: StateFlow<List<WalletItem>> = _walletList

    private val _walletAmountTotal = MutableStateFlow(0.0)
    val walletAmountTotal: StateFlow<Double> = _walletAmountTotal

    override fun updateState(state: LazyListState) {
        _lazyListState.value = state
    }

    init {
        fetchDataFromApi()
        fetchWalletData()
    }

    private fun fetchDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _dashboardList.value = List(500) { "Ini adalah data ke ${it + 1}" }
        }
    }

    private fun fetchWalletData() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _walletList.value = walletRepository.getWalletItems()
            _walletAmountTotal.value = _walletList.value.sumOf { it.amountDouble }
        }
    }

    fun refreshData(onComplete: () -> Unit) {
        val newList = List(500) { index -> "Data refreshed ke ${index + 1}" }
        _dashboardList.value = newList.toMutableList()

        onComplete()
    }

}