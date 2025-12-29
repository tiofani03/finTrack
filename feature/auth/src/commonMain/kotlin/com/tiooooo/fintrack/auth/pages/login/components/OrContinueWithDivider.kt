package com.tiooooo.fintrack.auth.pages.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.buttonPrimaryColor
import com.tiooooo.fintrack.component.theme.dividerColor
import com.tiooooo.fintrack.component.theme.textMedium12

@Composable
fun OrContinueWithDivider(
    modifier: Modifier = Modifier,
    text: String = "OR CONTINUE WITH"
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = dividerColor
        )

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = text,
            style = textMedium12().copy(
                color = buttonPrimaryColor,
                fontWeight = FontWeight.Medium
            )
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = dividerColor
        )
    }
}