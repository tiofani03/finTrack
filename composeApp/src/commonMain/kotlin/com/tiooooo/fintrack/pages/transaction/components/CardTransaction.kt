package com.tiooooo.fintrack.pages.transaction.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CardTransaction(
    modifier: Modifier = Modifier
) {

}

data class TransactionItem(
    val id: Int,
    val date: String,
    val purpose: String,
    val walletName: String,
    val type: Int,
    val amount: Int,
)