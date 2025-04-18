package com.tiooooo.fintrack.pages.wallet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium12
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium16
import com.tiooooo.fintrack.data.local.entity.WalletEntity
import com.tiooooo.fintrack.data.model.wallet.WalletItem
import com.tiooooo.fintrack.data.utils.formatRupiah
import com.tiooooo.fintrack.data.utils.formatToReadableString
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_login_google
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WalletCardItem(
    modifier: Modifier = Modifier,
    walletItem: WalletItem,
) {
    val textColor = if (walletItem.color == Color.White) {
        MaterialTheme.colorScheme.onSurface
    } else {
        Color.Black
    }

    val backgroundColor = if (walletItem.color == Color.White) {
        MaterialTheme.colorScheme.surface
    } else {
        walletItem.color
    }

    println(backgroundColor)

    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
        colors = CardDefaults.outlinedCardColors(
            containerColor = backgroundColor,
        ),
    ) {
        Column(
            modifier = Modifier
                .clickable { }
                .padding(MEDIUM_PADDING)
        ) {
            ElevatedCard(
                modifier = Modifier
                    .size(HUGE_PADDING),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp,
                ),
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(SMALL_PADDING),
                    painter = painterResource(walletItem.image),
                    contentDescription = null,
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = walletItem.name,
                style = textMedium12().copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                color = textColor,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Di-update ${walletItem.updatedAt}",
                style = textMedium10().copy(fontWeight = FontWeight.Thin),
                color = textColor,
            )
            Text(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING, bottom = MEDIUM_PADDING)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = formatRupiah(amount = walletItem.amount.toDoubleOrNull() ?: 0.0),
                style = textMedium16().copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = textColor,
            )
        }

    }
}

@Composable
fun WalletCardAddItem(
    modifier: Modifier = Modifier,
    onAddWalletClicked: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onAddWalletClicked.invoke()
                }
                .padding(MEDIUM_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ElevatedCard(
                modifier = Modifier
                    .size(72.dp)
                    .padding(SMALL_PADDING)
                    .clip(RoundedCornerShape(36.dp)),
                shape = CircleShape,
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp,
                )
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(LARGE_PADDING),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = EXTRA_SMALL_PADDING, bottom = EXTRA_LARGE_PADDING)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                text = "Tambah",
                style = textMedium14()
            )
        }
    }
}