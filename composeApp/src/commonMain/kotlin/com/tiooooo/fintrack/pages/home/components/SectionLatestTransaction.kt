package com.tiooooo.fintrack.pages.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.pages.transaction.components.CardTransaction
import com.tiooooo.fintrack.pages.transaction.components.TransactionItem

@Composable
fun SectionLatestTransaction(
    modifier: Modifier = Modifier,
    transactionItem: List<TransactionItem>,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(MEDIUM_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier.padding(bottom = MEDIUM_PADDING)
        ) {
            SectionTitleAndClickable(
                modifier = Modifier
                    .padding(
                        top = MEDIUM_PADDING,
                        start = MEDIUM_PADDING,
                        end = MEDIUM_PADDING,
                        bottom = SMALL_PADDING,
                    ),
                title = "Transaksi Terakhir",
                description = "Berikut beberapa transaksi terakhir kamu",
                clickableText = "Selengkapnya",
                onTextClick = {

                }
            )
            transactionItem.forEach { transaction ->
                CardTransaction(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SMALL_PADDING)
                        .padding(bottom = SMALL_PADDING),
                    transactionItem = transaction,
                    onTransactionClicked = {

                    }
                )
            }
        }
    }
}
