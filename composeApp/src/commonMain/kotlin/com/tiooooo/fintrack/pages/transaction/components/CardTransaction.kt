package com.tiooooo.fintrack.pages.transaction.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium12
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.data.utils.formatRupiah
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_transaction_chart
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardTransaction(
    modifier: Modifier = Modifier,
    transactionItem: TransactionItem,
    onTransactionClicked: (TransactionItem) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ElevatedCard(
            modifier = Modifier
                .weight(0.3f)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(36.dp)),
            shape = CircleShape,
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(EXTRA_SMALL_PADDING),
                painter = painterResource(Res.drawable.ic_transaction_chart),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .padding(start = SMALL_PADDING)
                .weight(2f)
                .align(Alignment.CenterVertically),
        ) {
            Text(
                text = transactionItem.purpose,
                style = textMedium12().copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.padding(top = SMALL_PADDING),
                text = transactionItem.date,
                style = textMedium10().copy(
                    fontWeight = FontWeight.Thin,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
            modifier = Modifier
                .weight(1.5f)
                .align(Alignment.CenterVertically)
                .padding(end = SMALL_PADDING),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                textAlign = TextAlign.End,
                text = transactionItem.printTransactionAmount(),
                style = textMedium14().copy(
                    fontWeight = FontWeight.SemiBold,
                    color = transactionItem.getColorTransaction(),
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(top = SMALL_PADDING),
                textAlign = TextAlign.End,
                text = transactionItem.walletName,
                style = textMedium10().copy(
                    fontWeight = FontWeight.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

data class TransactionItem(
    val id: Int,
    val date: String,
    val purpose: String,
    val walletName: String,
    val type: Int,
    val amount: Int,
)

fun List<TransactionItem>.calculateTotal(): Int {
    return this.fold(0) { total, transaction ->
        if (transaction.type == OUTCOME) {
            total - transaction.amount
        } else {
            total + transaction.amount
        }
    }
}

const val INCOME = 1
const val OUTCOME = 0

fun TransactionItem.isIncome(): Boolean {
    return this.type == INCOME
}

fun TransactionItem.printSymbolTransaction(): String {
    return if (isIncome()) "+" else "-"
}

fun TransactionItem.printTransactionAmount(): String {
    val amount = formatRupiah(this.amount.toDouble())
    return "${printSymbolTransaction()} $amount"
}

@Composable
fun TransactionItem.getColorTransaction(): Color {
    return if (isIncome()) MaterialTheme.colorScheme.onSurface.copy(0.7f) else Color.Red.copy(alpha = 0.7f)
}