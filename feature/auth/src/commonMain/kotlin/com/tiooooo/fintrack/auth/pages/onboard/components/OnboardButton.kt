package com.tiooooo.fintrack.auth.pages.onboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.LARGE_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardButton(
  modifier: Modifier = Modifier,
  painter: DrawableResource,
  onCardClicked: () -> Unit,
) {
  ElevatedCard(
    modifier = modifier
      .size(72.dp)
      .padding(SMALL_PADDING)
      .clip(RoundedCornerShape(36.dp))
      .clickable { onCardClicked.invoke() },
    shape = CircleShape,
  ) {
    Icon(
      modifier = Modifier
        .fillMaxSize()
        .padding(LARGE_PADDING),
      painter = painterResource(painter),
      contentDescription = null,
      tint = MaterialTheme.colorScheme.onSurface
    )
  }
}
