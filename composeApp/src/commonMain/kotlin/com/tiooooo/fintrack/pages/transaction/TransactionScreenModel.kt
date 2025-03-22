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
            _transactionList.value = generateRandomTransactionData(100)
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
            val newList = generateRandomTransactionData(100)
            _transactionList.value = newList.toMutableList()
            onComplete()
        }
    }

    private fun generateRandomTransactionData(numItems: Int): List<TransactionItem> {
        val random = Random.Default
        val transactionItems = mutableListOf<TransactionItem>()
        val purposes =
            listOf("Shopping", "Food", "Transport", "Entertainment", "Salary", "Investment")
        val walletNames = listOf("Cash", "Bank", "Crypto", "Gift Card")
        val dates = listOf(
            "2024-12-01", "2024-12-02", "2024-12-03", "2024-12-04", "2024-12-05",
            "2024-12-06", "2024-12-07", "2024-12-08", "2024-12-09", "2024-12-10"
        )


        for (i in 1..numItems) {

            val transactionItem = TransactionItem(
                id = random.nextInt(1000) + 1, // Random ID between 1 and 1000
                date = dates[random.nextInt(dates.size)],
                purpose = purposes[random.nextInt(purposes.size)],
                walletName = walletNames[random.nextInt(walletNames.size)],
                type = random.nextInt(2) + 1,
                amount = random.nextInt(5000) + 10
            )

            transactionItems.add(transactionItem)
        }

        return transactionItems
    }
}