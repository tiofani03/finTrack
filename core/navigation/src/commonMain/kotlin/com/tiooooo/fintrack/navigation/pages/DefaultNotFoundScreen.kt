package com.tiooooo.fintrack.navigation.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.resources.IconHelper
import com.tiooooo.fintrack.component.theme.EXTRA_EXTRA_HUGE_PADDING
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import org.jetbrains.compose.resources.painterResource

class DefaultNotFoundScreen : Screen {

  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
    BaseScaffold(
      topBarEnable = true,
      onBackClicked = { navigator.pop() }
    ) { innerPadding ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
          .offset(y = (-HUGE_PADDING))
          .padding(MEDIUM_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Image(
          painter = painterResource(IconHelper.emptyGhost),
          contentDescription = "404 Icon",
        )

        Spacer(modifier = Modifier.height(EXTRA_EXTRA_HUGE_PADDING))

        Text(
          text = "Halaman tidak ditemukan",
          style = MaterialTheme.typography.headlineSmall,
          color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))

        Text(
          text = "Halaman yang kamu cari mungkin sudah dipindahkan atau tidak tersedia.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING))

        Button(
          onClick = {
            navigator.pop()
          },
        ) {
          Text(
            text = "Kembali",
            modifier = Modifier.padding(horizontal = SMALL_PADDING),
          )
        }
      }
    }
  }
}
