package com.tiooooo.fintrack.pages.wallet

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.utils.formatRupiah
import com.tiooooo.fintrack.pages.wallet.components.WalletItem
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_login_apple
import fintrack.composeapp.generated.resources.ic_login_facebook
import fintrack.composeapp.generated.resources.ic_login_google
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class WalletScreenModel : ScreenModel {
    private val _lazyListState = MutableStateFlow(LazyGridState())
    val lazyListState: StateFlow<LazyGridState> = _lazyListState

    fun updateState(state: LazyGridState) {
        _lazyListState.value = state
    }

    private val _walletList = MutableStateFlow<List<WalletItem>>(emptyList())
    val walletList: StateFlow<List<WalletItem>> = _walletList

    private val _walletAmountTotal = MutableStateFlow<Double>(0.0)
    val walletAmountTotal: StateFlow<Double> = _walletAmountTotal

    init {
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _walletList.value = generateDummyWalletItems(15)
            _walletAmountTotal.value = _walletList.value.sumOf { it.amountDouble }
        }
    }

    private fun generateDummyWalletItem(): WalletItem {
        val id = Random.nextInt(1, 1000)  // ID acak antara 1 dan 1000
        val names = listOf("Bank Bca", "Bank Jago", "Duit Cash")
        val name = names.random()  // Memilih nama acak dari daftar
        val amount = Random.nextInt(10000, 1000000)
        val color = listOf(
            Color.Red,
            Color.Gray,
            Color.Green,
            Color.Yellow,
            Color.Blue
        ).random()  // Pilih warna acak
        val image = listOf(
            Res.drawable.ic_login_apple,
            Res.drawable.ic_login_google,
            Res.drawable.ic_login_facebook,
        ).random()

        return WalletItem(
            id = id,
            name = name,
            amount = formatRupiah(amount.toDouble()),
            amountDouble = amount.toDouble(),
            color = color,
            image = image,
        )
    }

    private fun generateDummyWalletItems(count: Int): List<WalletItem> {
        return List(count) { generateDummyWalletItem() }
    }
}