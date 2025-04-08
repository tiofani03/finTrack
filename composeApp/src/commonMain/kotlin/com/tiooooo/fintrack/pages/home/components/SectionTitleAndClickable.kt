package com.tiooooo.fintrack.pages.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium16

@Composable
fun SectionTitleAndClickable(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    clickableText: String? = null,
    onTextClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier,
                text = title,
                style = textMedium16().copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                modifier = Modifier
                    .padding(
                        top = EXTRA_SMALL_PADDING,
                    ),
                text = description,
                textAlign = TextAlign.Start,
                style = textMedium10().copy(
                    fontWeight = FontWeight.ExtraLight,
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (!clickableText.isNullOrEmpty() && onTextClick != null) {
            Text(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onTextClick.invoke()
                    }
                    .padding(vertical = MEDIUM_PADDING)
                    .align(Alignment.CenterVertically),
                text = clickableText,
                style = textMedium10().copy(
                    fontWeight = FontWeight.Bold,
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}