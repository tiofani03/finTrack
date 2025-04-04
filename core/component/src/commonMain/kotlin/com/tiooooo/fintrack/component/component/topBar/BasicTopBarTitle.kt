package com.tiooooo.fintrack.component.component.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun BasicTopBarTitle(
    modifier: Modifier = Modifier,
    title: String,
    onIconClicked: (() -> Unit)? = null,
    iconContent: (@Composable () -> Unit)? = null,
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
            text = title,
            style = textMedium22().copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        IconButton(
            onClick = { onIconClicked?.invoke() },
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            iconContent?.invoke()
        }
    }
}