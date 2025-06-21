package com.tiooooo.fintrack.auth

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

class SampleAuthScreen : Screen {
  @Composable
  override fun Content() {
    val nav = rememberNavHelper()

    BaseScaffold {
      Column(modifier = Modifier.fillMaxSize().padding(it)) {
        Text(text = "Sample Auth Screen")
        Button(onClick = {
          nav.navigate("/dashboard")
        }) {
          Text(text = "Login")
        }
        Button(onClick = {
          nav.navigate("/auth/register/123?ref=abcdef")
        }) {
          Text(text = "Register")
        }
      }
    }
  }
}

class SampleRegisterScreen(private val id: String, private val ref: String?) : Screen {
  @Composable
  override fun Content() {
    val nav = rememberNavHelper()

    BaseScaffold {
      Column(modifier = Modifier.fillMaxSize().padding(it)) {
        Text(text = "Sample Register Screen $id $ref")
        Button(onClick = {
          nav.navigate("/dashboardo")
        }) {
          Text(text = "Login")
        }
      }
    }
  }
}
