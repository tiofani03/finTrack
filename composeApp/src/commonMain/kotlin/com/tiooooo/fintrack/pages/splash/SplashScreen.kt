package com.tiooooo.fintrack.pages.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium12
import com.tiooooo.fintrack.component.theme.textMedium20
import com.tiooooo.fintrack.getPlatform
import com.tiooooo.fintrack.navigation.rememberNavHelper
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource


@Composable
fun SplashScreen(
  modifier: Modifier = Modifier,
  splashScreenModel: SplashScreenModel,
) {
  val navigator = rememberNavHelper()

  // Animation specs
  val fadeInSpec = fadeIn(animationSpec = tween(durationMillis = 600, easing = EaseOutCubic))
  val slideInSpec = slideInVertically(
    initialOffsetY = { it / 4 },
    animationSpec = tween(durationMillis = 600, easing = EaseIn)
  )
  val enterAnim = fadeInSpec + slideInSpec

  // Visibility state
  val (logoVisible, setLogoVisible) = remember { mutableStateOf(false) }
  val (titleVisible, setTitleVisible) = remember { mutableStateOf(false) }
  val (subtitleVisible, setSubtitleVisible) = remember { mutableStateOf(false) }

  // Handle animation sequence + navigation decision
  LaunchedEffect(Unit) {
    delay(400)
    setLogoVisible(true)
    delay(300)
    setTitleVisible(true)
    delay(250)
    setSubtitleVisible(true)
    delay(800)

//    splashScreenModel.dispatch(SplashIntent.CheckLoggedIn)
  }

  // Handle navigation effect
  LaunchedEffect(Unit) {
    splashScreenModel.effect.collect { effect ->
      when (effect) {
        is SplashEffect.NavigateToOnboard -> {
          navigator.replaceAll("/auth/onboard")
        }

        is SplashEffect.NavigateToDashboard -> {
          navigator.replaceAll("/dashboard")
        }
      }
    }
  }

  BaseScaffold { paddingValues ->
    Box(
      modifier = modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center)
          .padding(bottom = MEDIUM_PADDING),
      ) {
        AnimatedVisibility(visible = logoVisible, enter = enterAnim) {
          Image(
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
              .padding(horizontal = EXTRA_LARGE_PADDING),
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
          )
        }

        AnimatedVisibility(visible = titleVisible) {
          Text(
            modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .padding(vertical = SMALL_PADDING, horizontal = EXTRA_LARGE_PADDING),
            text = "FinTrack",
            style = textMedium20().copy(
              fontWeight = FontWeight.Bold,
              color = Color.White
            ),
          )
        }

        AnimatedVisibility(visible = subtitleVisible) {
          Text(
            modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .padding(horizontal = EXTRA_LARGE_PADDING),
            text = "Gak Cuma Gaya, Keuangan Juga Harus Keren",
            style = textMedium12().copy(
              fontWeight = FontWeight.Light,
              color = Color.White
            ),
          )
        }
      }

      Text(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = paddingValues.calculateBottomPadding() + MEDIUM_PADDING),
        text = "Versi 1.0.0 ",
        style = textMedium12().copy(
          fontWeight = FontWeight.Normal,
          color = Color.White
        ),
      )
    }
  }
}
