package com.tiooooo.fintrack.feature.settings.pages.settings.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.ICON_CARD_SIZE
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium16

data class SettingItem(
    val title: String,
    val icon: ImageVector? = null,
    val textColor: Color? = null,
    val iconTint: Color? = null,
    val actionContent: @Composable (() -> Unit)? = null,
    val onClick: () -> Unit = { },
)

@Composable
fun SettingGroupSection(
    modifier: Modifier = Modifier,
    title: String,
    items: List<SettingItem>,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = MEDIUM_PADDING, top = SMALL_PADDING),
            text = title,
            style = textMedium16().copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))

        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING)
                .clip(RoundedCornerShape(MEDIUM_PADDING)),
            shape = RoundedCornerShape(MEDIUM_PADDING),
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
        ) {
            items.forEachIndexed { index, item ->
                SettingListSection(item)
                if (index != items.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                    )
                }
            }
        }
    }
}

@Composable
fun SettingListSection(item: SettingItem) {
    Row(
        modifier = Modifier
            .clickable {
                item.onClick.invoke()
            }
            .fillMaxWidth()
            .padding(MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(ICON_CARD_SIZE)
                .clip(RoundedCornerShape(MEDIUM_PADDING))
                .background(
                    color =
                        item.iconTint?.copy(0.1f)
                            ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ),

            ) {
            item.icon?.let {
                Icon(
                    modifier = Modifier.padding(SMALL_PADDING),
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconTint ?: MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.width(MEDIUM_PADDING))

        Text(
            text = item.title,
            style = textMedium14().copy(
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.weight(1f),
            color = item.textColor ?: Color.Unspecified
        )

        if (item.actionContent != null) {
            item.actionContent.invoke()
        } else {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
        }
    }
}