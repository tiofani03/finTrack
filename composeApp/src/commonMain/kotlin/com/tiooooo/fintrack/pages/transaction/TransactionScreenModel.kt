package com.tiooooo.fintrack.pages.transaction

import androidx.compose.foundation.lazy.LazyListState
import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import com.tiooooo.fintrack.pages.transaction.components.TransactionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class TransactionScreenModel : ScreenModel, ScrollStateManager {
    private val _transactionList = MutableStateFlow<List<TransactionItem>>(emptyList())
    val transactionList: StateFlow<List<TransactionItem>> = _transactionList

    init {
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _transactionList.value = generateRandomTransactionData(20)
        }
    }

    private val _lazyListState = MutableStateFlow(LazyListState())
    override val lazyListState: StateFlow<LazyListState> = _lazyListState

    override fun updateState(state: LazyListState) {
        _lazyListState.value = state
    }

    fun refreshData(onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            val newList = generateRandomTransactionData(10000)
            _transactionList.value = newList.toMutableList()
            onComplete()
        }
    }

    private fun generateRandomTransactionData(numItems: Int): List<TransactionItem> {
        val random = Random.Default
        val transactionItems = mutableListOf<TransactionItem>()
        val purposes =
            listOf("Makan", "Transportasi", "Lainnya", "Transfer", "Gaji", "Investasi")
        val walletNames = listOf("BCA", "Jago", "Dompet", "Tabungan")
        val dates = listOf(
            "23 Mar 2025", "24 Mar 2025", "25 Mar 2025, 26 Mar 2025, 27 Mar 2025", "28 Mar 2025", "29 Mar 2025",
            "30 Mar 2025", "31 Mar 2025", "1 Apr 2025", "2 Apr 2025", "3 Apr 2025",
        )


        for (i in 1..numItems) {
            val transactionItem = TransactionItem(
                id = random.nextInt(100000) + 1, // Random ID between 1 and 1000
                date = dates[random.nextInt(dates.size)],
                purpose = purposes[random.nextInt(purposes.size)],
                walletName = walletNames[random.nextInt(walletNames.size)],
                type = random.nextInt(2),
                amount = random.nextInt(10000000) + 10
            )

            transactionItems.add(transactionItem)
        }

        return transactionItems
    }
}