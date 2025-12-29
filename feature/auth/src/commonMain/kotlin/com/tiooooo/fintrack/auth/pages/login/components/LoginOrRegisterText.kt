package com.tiooooo.fintrack.auth.pages.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.theme.buttonPrimaryColor
import com.tiooooo.fintrack.component.theme.textMedium14

@Composable
fun LoginOrRegisterText(
    modifier: Modifier = Modifier,
    firstText: String = "Don't have an account?",
    highlightedText: String = "Register now",
    onRegisterClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$firstText ",
            style = textMedium14()
        )

        Text(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onRegisterClick()
            },
            text = highlightedText,
            style = textMedium14().copy(
                color = buttonPrimaryColor,
                fontWeight = FontWeight.SemiBold
            ),
        )
    }
}


