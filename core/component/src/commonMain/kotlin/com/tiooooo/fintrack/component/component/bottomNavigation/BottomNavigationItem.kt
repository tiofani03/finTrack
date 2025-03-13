package com.tiooooo.fintrack.component.component.bottomNavigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lm.kmp.core.component.theme.MEDIUM_PADDING
import com.lm.kmp.core.component.theme.textMedium10

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    bottomNavModel: BottomNavModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
        animationSpec = tween(durationMillis = 400)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(MEDIUM_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.fillMaxWidth(),
            imageVector = if (isSelected) bottomNavModel.iconSelected else bottomNavModel.iconNotSelected,
            contentDescription = bottomNavModel.label,
            tint = backgroundColor
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = bottomNavModel.label,
            color = backgroundColor,
            style = textMedium10(),
        )
    }
}