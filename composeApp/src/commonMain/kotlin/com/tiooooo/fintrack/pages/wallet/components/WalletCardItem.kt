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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WalletCardItem(
    modifier: Modifier = Modifier,
    walletItem: WalletItem,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
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
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "Di-update 12 Mar 18.30",
                style = textMedium10().copy(fontWeight = FontWeight.Thin),
            )
            Text(
                modifier = Modifier
                    .padding(top = MEDIUM_PADDING, bottom = MEDIUM_PADDING)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = walletItem.amount,
                style = textMedium16().copy(
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }

    }
}

@Composable
fun WalletCardAddItem(
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .clickable {

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


data class WalletItem(
    val id: Int,
    val name: String,
    val amount: String,
    val amountDouble: Double,
    val color: Color,
    val image: DrawableResource,
)