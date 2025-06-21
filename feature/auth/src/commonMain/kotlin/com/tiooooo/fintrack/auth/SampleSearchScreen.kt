package com.tiooooo.fintrack.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class SampleSearchScreen : Screen {
  @Composable
  override fun Content() {
    Box {
       Text("Search Screen")
    }
  }
}

class SampleProfile : Screen {
  @Composable
  override fun Content() {
    Box {
      Text("Profile Screen")
    }
  }
}