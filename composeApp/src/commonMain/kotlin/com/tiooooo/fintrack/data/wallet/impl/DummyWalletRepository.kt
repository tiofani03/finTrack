package com.tiooooo.fintrack.data.wallet.impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingFlat
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.ui.graphics.Color
import com.tiooooo.fintrack.component.utils.formatRupiah
import com.tiooooo.fintrack.data.wallet.api.WalletRepository
import com.tiooooo.fintrack.pages.home.components.SummaryItem
import com.tiooooo.fintrack.pages.transaction.components.TransactionItem
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

    override suspend fun getSummaryItems() = generateDummySummaryItem()
    override suspend fun getLatestTransaction() = generateRandomTransactionData(5)

    private fun generateRandomTransactionData(numItems: Int): List<TransactionItem> {
        val random = Random.Default
        val transactionItems = mutableListOf<TransactionItem>()
        val purposes =
            listOf("Makan", "Transportasi", "Lainnya", "Transfer", "Gaji", "Investasi")
        val walletNames = listOf("BCA", "Jago", "Dompet", "Tabungan")
        val dates = listOf(
            "23 Mar 2025",
            "24 Mar 2025",
            "25 Mar 2025",
            "26 Mar 2025",
            "27 Mar 2025",
            "28 Mar 2025",
            "29 Mar 2025",
            "30 Mar 2025",
            "31 Mar 2025",
            "1 Apr 2025",
            "2 Apr 2025",
            "3 Apr 2025",
        )


        for (i in 1..numItems) {
            val transactionItem = TransactionItem(
                id = random.nextInt(1000) + 1, // Random ID between 1 and 1000
                date = dates[random.nextInt(dates.size)],
                purpose = purposes[random.nextInt(purposes.size)],
                walletName = walletNames[random.nextInt(walletNames.size)],
                type = random.nextInt(2),
                amount = random.nextInt(1000) + 10
            )

            transactionItems.add(transactionItem)
        }

        return transactionItems
    }

    private fun generateDummySummaryItem(): List<SummaryItem> {
        val dibelanjakan = SummaryItem(
            id = 1,
            title = "Rp404.000",
            desc = "Dibelanjakan",
            backgroundColor = Color.Red,
            icon = Icons.AutoMirrored.Filled.TrendingFlat
        )

        val ditabung = SummaryItem(
            id = 2,
            title = "Rp404.000",
            desc = "Ditabung",
            backgroundColor = Color.Green,
            icon = Icons.AutoMirrored.Filled.TrendingUp
        )

        val wallet = SummaryItem(
            id = 3,
            title = "12 Dompet",
            desc = "Jumlah semua akun kamu",
            backgroundColor = Color.Magenta,
            icon = Icons.Default.AccountBalanceWallet
        )

        val amount = SummaryItem(
            id = 4,
            title = "Saldo naik 5%",
            desc = "dari bulan lalu",
            backgroundColor = Color.Blue,
            icon = Icons.AutoMirrored.Filled.TrendingUp
        )

        return listOf(
            dibelanjakan, ditabung, wallet, amount
        )

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
