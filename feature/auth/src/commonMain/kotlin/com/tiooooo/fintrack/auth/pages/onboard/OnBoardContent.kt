package com.tiooooo.fintrack.auth.pages.onboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.tiooooo.fintrack.auth.pages.onboard.components.OnBoardStories
import com.tiooooo.fintrack.auth.pages.onboard.components.OnboardBottomSheet
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.primaryDark
import com.tiooooo.fintrack.component.theme.primaryLight
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium20
import com.tiooooo.fintrack.navigation.rememberNavHelper
import kotlinx.coroutines.delay
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.ToastGravity
import multiplatform.network.cmptoast.showToast
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardContent(
  modifier: Modifier = Modifier,
  onboardScreenModel: OnboardScreenModel,
) {
  val nav = rememberNavHelper()
  val listOfImages = onboardScreenModel.listOfImages
  val listOfText = onboardScreenModel.listOfText

  var isVisibleBottomSheet by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    onboardScreenModel.effect.collect {
      when (it) {
        is OnBoardEffect.ShowToast -> {
          showToast(
            message = it.message,
            gravity = ToastGravity.Bottom,
            duration = ToastDuration.Short
          )
        }

        is OnBoardEffect.NavigateToDashboard -> {
          nav.replaceAll("/dashboard")
        }

        else -> Unit
      }
    }
  }

  LaunchedEffect(Unit) {
    delay(200)
    isVisibleBottomSheet = true
  }

  BaseScaffold(
    modifier = modifier,
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = paddingValues.calculateBottomPadding())
        .background(Brush.verticalGradient(listOf(primaryDark, primaryLight)))
    ) {
      OnBoardStories(
        modifier = Modifier
          .fillMaxHeight(0.75f)
          .padding(top = paddingValues.calculateTopPadding())
          .fillMaxWidth()
          .padding(bottom = MEDIUM_PADDING),
        numberOfPages = listOfImages.size,
        onEveryStoryChange = { position ->

        },
        onComplete = {

        },
      ) { index ->
        Box(modifier = Modifier.fillMaxSize()) {
          Image(
            modifier = Modifier
              .fillMaxHeight(0.55f)
              .align(Alignment.Center)
              .padding(horizontal = EXTRA_LARGE_PADDING)
              .padding(bottom = MEDIUM_PADDING),
            painter = painterResource(listOfImages[index]),
            contentDescription = null,
          )
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .align(Alignment.BottomCenter)
              .padding(bottom = MEDIUM_PADDING)
          ) {
            androidx.compose.material3.Text(
              modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = MEDIUM_PADDING),
              text = listOfText[index],
              style = textMedium20().copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
              )
            )
            androidx.compose.material3.Text(
              modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                  top = SMALL_PADDING,
                  start = MEDIUM_PADDING,
                  end = MEDIUM_PADDING,
                ),
              text = "Pake FinTrack, keuanganmu bakal lebih terkontrol tanpa drama.",
              style = textMedium14().copy(
                color = Color.White,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
              )
            )
          }
        }
      }

      AnimatedVisibility(
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.25f)
          .align(Alignment.BottomCenter),
        visible = isVisibleBottomSheet,
        enter = slideInVertically(
          initialOffsetY = { fullHeight -> fullHeight },
          animationSpec = tween(durationMillis = 500, easing = EaseOutCubic)
        ) + fadeIn(animationSpec = tween(500))
      ) {
        OnboardBottomSheet(
          modifier = Modifier
            .background(
              shape = RoundedCornerShape(
                topStart = EXTRA_LARGE_PADDING,
                topEnd = EXTRA_LARGE_PADDING,
              ),
              color = MaterialTheme.colorScheme.background,
            ),
          onGoogleResult = { result ->
            result.onSuccess {
              onboardScreenModel.dispatch(OnBoardIntent.RegisterSuccess(it))
            }.onFailure { error ->
              onboardScreenModel.dispatch(OnBoardIntent.ShowToast(error.message.orEmpty()))
            }
          }
        )
      }
    }
  }
}