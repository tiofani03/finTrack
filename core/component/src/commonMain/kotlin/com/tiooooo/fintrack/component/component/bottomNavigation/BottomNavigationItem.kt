package com.tiooooo.fintrack.component.component.bottomNavigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.tiooooo.fintrack.component.theme.LARGE_PADDING
import com.tiooooo.fintrack.component.theme.textMedium10
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    bottomNavModel: BottomNavModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.6f
        ),
        animationSpec = tween(durationMillis = 400)
    )

    val painter = remember {
        mutableStateOf(bottomNavModel.iconNotSelected)
    }.apply {
        value = if (isSelected) bottomNavModel.iconSelected else bottomNavModel.iconNotSelected
    }.value

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(LARGE_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.height(LARGE_PADDING),
            painter = painterResource(painter),
            contentDescription = bottomNavModel.label,
            tint = backgroundColor
        )
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = bottomNavModel.label,
            color = backgroundColor,
            style = textMedium10(),
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
