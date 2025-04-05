package com.tiooooo.fintrack.data.wallet.impl

import androidx.compose.ui.graphics.Color
import com.tiooooo.fintrack.component.utils.formatRupiah
import com.tiooooo.fintrack.data.wallet.api.WalletRepository
import com.tiooooo.fintrack.pages.wallet.components.WalletItem
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_login_apple
import fintrack.composeapp.generated.resources.ic_login_facebook
import fintrack.composeapp.generated.resources.ic_login_google
import kotlinx.coroutines.delay
import kotlin.random.Random

class DummyWalletRepository : WalletRepository {

    override suspend fun getWalletItems(): List<WalletItem> {
        delay(1000)
        return generateDummyWalletItems(15)
    }

    private fun generateDummyWalletItem(): WalletItem {
        val id = Random.nextInt(1, 1000)
        val names = listOf("Bank BCA", "Bank Jago", "Duit Cash")
        val name = names.random()
        val amount = Random.nextInt(10000, 1000000)
        val color = listOf(
            Color.Red,
            Color.Gray,
            Color.Green,
            Color.Yellow,
            Color.Blue
        ).random()
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
