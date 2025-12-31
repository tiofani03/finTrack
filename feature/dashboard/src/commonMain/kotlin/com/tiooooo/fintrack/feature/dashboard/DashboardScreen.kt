package com.tiooooo.fintrack.feature.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.navigation.rememberNavHelper
import com.tiooooo.fintrack.navigation.rememberRouteResolver

class DashboardScreen : Screen {

  @Composable
  override fun Content() {

    val resolver = rememberRouteResolver()
    var currentTab by rememberSaveable { mutableStateOf("/home") }

    // cache Screen supaya instance sama
    val screenCache = remember {
      mutableMapOf<String, Screen>()
    }

    fun resolve(path: String): Screen =
      screenCache.getOrPut(path) {
        resolver.require(path)
      }

    // SATU Navigator SAJA
    Navigator(resolve("/home")) { navigator ->

      // ganti root screen TANPA dispose tab lama
      LaunchedEffect(currentTab) {
        val target = resolve(currentTab)

        if (navigator.lastItem != target) {
          navigator.replaceAll(target)
        }
      }

      BaseScaffold { innerPadding ->
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {

          // CONTENT TAB
          Box(modifier = Modifier.weight(1f)) {
            CurrentScreen()
          }

          // BOTTOM BAR
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
          ) {
            TabButton("Home", "/home", currentTab) { currentTab = it }
            TabButton("Search", "/auth/onboard", currentTab) { currentTab = it }
            TabButton("Profile", "/profile", currentTab) { currentTab = it }
          }
        }
      }
    }
  }

  @Composable
  private fun TabButton(
    label: String,
    path: String,
    currentTab: String,
    onClick: (String) -> Unit
  ) {
    Text(
      text = label,
      color = if (currentTab == path) Color.Blue else Color.Black,
      modifier = Modifier.clickable { onClick(path) }
    )
  }
}




class SampleHome : Screen {
  @Composable
  override fun Content() {
    Box {
      Text("Home Screen")
    }
  }
}
