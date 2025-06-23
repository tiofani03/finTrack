package com.tiooooo.fintrack.auth.pages.onboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tiooooo.fintrack.component.resources.IconHelper
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium18

@Composable
fun OnboardBottomSheet(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      modifier = Modifier.padding(top = EXTRA_LARGE_PADDING),
      text = "Login or signup with",
      style = textMedium18().copy(
        fontWeight = FontWeight.Bold
      )
    )

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(
          horizontal = MEDIUM_PADDING
        )
        .padding(top = SMALL_PADDING),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      OnboardButton(
        painter = IconHelper.icLoginGoogle,
        onCardClicked = {

        }
      )
      OnboardButton(
        painter = IconHelper.icLoginApple,
        onCardClicked = {

        }
      )
      OnboardButton(
        painter = IconHelper.icLoginFacebook,
        onCardClicked = {

        }
      )
    }
  }
}