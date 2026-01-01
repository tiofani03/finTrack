package com.tiooooo.fintrack.component.component.sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING

data class RadioItem<T>(
    val id: T,
    val label: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> RadioButtonBottomSheet(
    visible: Boolean,
    title: String? = null,
    items: List<RadioItem<T>>,
    selectedId: T?,
    paddingValues: PaddingValues? = null,
    onSelected: (RadioItem<T>) -> Unit,
    onDismiss: () -> Unit
) {
    if (!visible) return

    BaseModalBottomSheet(
        paddingValues = paddingValues,
        onDismiss = onDismiss
    ) {
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING)
            )
        }

        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelected(item) }
                    .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = item.id == selectedId,
                    onClick = { onSelected(item) }
                )
                Spacer(modifier = Modifier.width(SMALL_PADDING))
                Text(text = item.label)
            }
        }
    }
}

