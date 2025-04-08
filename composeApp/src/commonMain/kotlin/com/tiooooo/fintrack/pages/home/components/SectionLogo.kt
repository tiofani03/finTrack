package com.tiooooo.fintrack.pages.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.textMedium18
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun SectionLogo(
    modifier: Modifier = Modifier,
    isScrolled: Boolean,
) {
    val navigator = LocalNavigator.currentOrThrow

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(HUGE_PADDING),
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )
        AnimatedVisibility(
            visible = !isScrolled
        ) {
            Text(
                text = "FinTrack",
                style = textMedium18().copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BadgedBox(badge = { Badge() }) {
            Icon(
                modifier = Modifier
                    .size(EXTRA_LARGE_PADDING)
                    .clickable {},
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifikasi"
            )
        }
    }
}
