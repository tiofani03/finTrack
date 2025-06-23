package com.tiooooo.fintrack.feature.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.navigation.rememberNavHelper

class SampleDashboardScreen : Screen {
  @Composable
  override fun Content() {
    val nav = rememberNavHelper()

    BaseScaffold {
      Column(modifier = Modifier.fillMaxSize().padding(it)) {
        Text(text = "Sample Dashboard Screen")
        Button(onClick = {
          nav.replaceAll("/main")
        }) {
          Text(text = "Dashboard")
        }
      }
    }
  }
}