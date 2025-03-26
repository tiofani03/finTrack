package com.tiooooo.fintrack.pages.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.textMedium22
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_transaction_chart
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionPageTitle(
    modifier: Modifier = Modifier,
    onChartClicked: () -> Unit,
) {
    Box(modifier = modifier) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.13f)
                .background(Color.Transparent),
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = MEDIUM_PADDING),
            textAlign = TextAlign.Start,
            text = "Transaksi",
            style = textMedium22().copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        IconButton(
            onClick = { onChartClicked.invoke() },
            modifier = Modifier.align(Alignment.CenterEnd).padding(
                top = MEDIUM_PADDING,
            ),
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_transaction_chart),
                contentDescription = null,
            )
        }
    }
}
