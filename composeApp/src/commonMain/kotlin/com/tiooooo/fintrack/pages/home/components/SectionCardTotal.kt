package com.tiooooo.fintrack.pages.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.FinTrackAppColors
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium12
import com.tiooooo.fintrack.component.theme.textMedium18
import com.tiooooo.fintrack.data.utils.formatRupiah

@Composable
fun SectionCardTotal(
    modifier: Modifier = Modifier,
    walletAmount: Double,
    onTransactionClicked: () -> Unit,
) {
    val appColors = FinTrackAppColors.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        appColors.backgroundPrimaryLightColor,
                        appColors.backgroundPrimaryDarkColor,
                    )
                ),
                shape = RoundedCornerShape(MEDIUM_PADDING),
            )
            .clip(RoundedCornerShape(MEDIUM_PADDING))
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            drawCircle(
                color = Color.White.copy(alpha = 0.15f),
                radius = size.minDimension / 1.4f,
                center = Offset(x = size.width * 0.1f, y = size.height * 0.3f)
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.2f),
                radius = size.minDimension / 3f,
                center = Offset(x = size.width * 0.8f, y = size.height * 0.8f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING, vertical = HUGE_PADDING)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = "Total tabunganmu",
                        color = appColors.textWhiteColor,
                        style = textMedium12().copy(fontWeight = FontWeight.ExtraLight),
                    )
                    Text(
                        modifier = Modifier.padding(top = EXTRA_SMALL_PADDING),
                        text = formatRupiah(walletAmount),
                        color = appColors.textWhiteColor,
                        style = textMedium18().copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Lihat Riwayat",
                    style = textMedium10().copy(
                        fontWeight = FontWeight.Bold,
                        color = appColors.backgroundPrimaryLightColor,
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(MEDIUM_PADDING))
                        .clickable { onTransactionClicked.invoke() }
                        .background(appColors.textWhiteColor)
                        .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING)
                )
            }
        }
    }
}
