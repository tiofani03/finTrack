package com.tiooooo.fintrack.pages.transaction.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.utils.formatRupiah

@Composable
fun CardTransactionDate(
    modifier: Modifier = Modifier,
    date: String,
    transactionCount: Int,
    totalAmount: Int,
) {
    val transactionColor =
        if (totalAmount < 0) Color.Red.copy(0.7f) else MaterialTheme.colorScheme.onSurface.copy(0.7f)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = date,
                style = textMedium14().copy(
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(
                modifier = Modifier.padding(top = EXTRA_SMALL_PADDING),
                text = "$transactionCount Transaksi",
                style = textMedium10().copy(
                    fontWeight = FontWeight.ExtraLight,
                )
            )
        }

        Text(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .align(Alignment.CenterVertically),
            text = formatRupiah(totalAmount.toDouble()),
            style = textMedium14().copy(
                fontWeight = FontWeight.SemiBold,
                color = transactionColor,
            )
        )
    }
}
