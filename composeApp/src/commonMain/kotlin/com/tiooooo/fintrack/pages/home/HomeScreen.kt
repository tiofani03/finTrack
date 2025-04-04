package com.tiooooo.fintrack.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tiooooo.fintrack.component.theme.SMALL_PADDING

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenModel: HomeScreenModel,
) {
    val homeList by homeScreenModel.dashboardList.collectAsState()
    val listState by homeScreenModel.lazyListState.collectAsState()



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FA)),
        state = listState
    ) {
        // 1. Header Keuangan
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF007AFF)) // Biru khas keuangan
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Keuangan Saya",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Rp7.201.070",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }
        }

        // 2. Menu Shortcut (dibuat horizontal dengan LazyRow)
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                items(shortcutMenu) { menu ->
                    ShortcutButton(menu.icon, menu.label)
                }
            }
        }

        // 3. List Transaksi
        items(dummyTransactions) { transaction ->
            TransactionItem(transaction)
        }
    }
//    LazyColumn(
//        modifier = modifier,
//        state = listState
//    ) {
//        items(homeList.size) {
//            Text(
//                modifier = Modifier.padding(SMALL_PADDING),
//                text = homeList.get(index = it)
//            )
//        }
//    }
//
//    homeScreenModel.updateState(listState)
}


// Komponen untuk Tombol Shortcut
@Composable
fun ShortcutButton(icon: ImageVector, label: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, modifier = Modifier.size(32.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

// Komponen untuk Item Transaksi
@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = transaction.category, fontWeight = FontWeight.Bold)
                Text(text = transaction.date, fontSize = 12.sp, color = Color.Gray)
            }
            Text(
                text = "Rp${transaction.amount}",
                color = if (transaction.amount < 0) Color.Red else Color.Green
            )
        }
    }
}

// Dummy Data untuk Transaksi
data class Transaction(val category: String, val date: String, val amount: Int)

val dummyTransactions = listOf(
    Transaction("Makan", "12 Mar 2025", -50000),
    Transaction("Gaji", "10 Mar 2025", 5000000),
    Transaction("Belanja", "08 Mar 2025", -150000),
    Transaction("Transport", "07 Mar 2025", -20000),
    Transaction("Investasi", "05 Mar 2025", 300000)
)

// Dummy Data untuk Shortcut Menu
data class ShortcutMenu(val icon: ImageVector, val label: String)

val shortcutMenu = listOf(
    ShortcutMenu(Icons.Default.AccountBalanceWallet, "Dompet"),
    ShortcutMenu(Icons.Default.Send, "Transfer"),
    ShortcutMenu(Icons.Default.BarChart, "Laporan"),
    ShortcutMenu(Icons.Default.Settings, "Pengaturan")
)

