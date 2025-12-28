package com.tiooooo.fintrack.component.component.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.theme.BUTTON_SIZE
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.buttonPrimaryColor
import com.tiooooo.fintrack.component.theme.textMedium14
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    icon: DrawableResource? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(BUTTON_SIZE)
            .clip(RoundedCornerShape(MEDIUM_PADDING)),
        shape = RoundedCornerShape(MEDIUM_PADDING),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonPrimaryColor,
        ),
        onClick = onClick,
        enabled = enabled
    ) {
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.padding(end = SMALL_PADDING)
            )
        }
        Text(
            text = text,
            style = textMedium14().copy(fontWeight = FontWeight.SemiBold),
        )
    }
}

@Composable
fun ButtonPrimarySmall(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(HUGE_PADDING)
            .clip(RoundedCornerShape(MEDIUM_PADDING)),
        shape = RoundedCornerShape(MEDIUM_PADDING),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonPrimaryColor,
        ),
        onClick = onClick,
        enabled = enabled
    ) {
        icon?.invoke()
        Text(
            text = text,
            style = textMedium14().copy(fontWeight = FontWeight.SemiBold),
        )
    }
}