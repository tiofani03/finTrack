package com.tiooooo.fintrack.pages.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium12

@Composable
fun SummarySection(
    modifier: Modifier = Modifier,
    summaryList: List<SummaryItem>,
    summaryListState: LazyListState,
) {
    LazyRow(
        modifier = modifier,
        state = summaryListState,
        contentPadding = PaddingValues(horizontal = MEDIUM_PADDING),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(summaryList, key = { _, item -> item.id }) { _, summary ->
            SummaryCard(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(SMALL_PADDING))
                    .background(summary.backgroundColor.copy(0.1f))
                    .padding(12.dp),
                summaryItem = summary
            )
        }
    }
}

@Composable
fun SummaryCard(
    modifier: Modifier = Modifier,
    summaryItem: SummaryItem,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = summaryItem.icon,
            contentDescription = null,
            tint = summaryItem.backgroundColor.copy(0.4f),
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = EXTRA_SMALL_PADDING)
                .wrapContentWidth(),
        ) {
            Text(
                modifier = Modifier,
                text = summaryItem.title,
                style = textMedium12().copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = summaryItem.desc,
                style = textMedium10().copy(
                    fontWeight = FontWeight.ExtraLight
                )
            )
        }
    }
}


data class SummaryItem(
    val id: Int,
    val title: String,
    val desc: String,
    val backgroundColor: Color,
    val icon: ImageVector,
)