package com.tiooooo.fintrack.auth.pages.onboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnBoardStories(
  modifier: Modifier = Modifier,
  numberOfPages: Int,
  indicatorModifier: Modifier = Modifier
    .padding(top = 12.dp, bottom = 12.dp)
    .clip(RoundedCornerShape(12.dp)),
  spaceBetweenIndicator: Dp = 4.dp,
  indicatorBackgroundColor: Color = Color.LightGray,
  indicatorProgressColor: Color = Color.White,
  slideDurationInSeconds: Long = 5,
  touchToPause: Boolean = true,
  hideIndicators: Boolean = false,
  onEveryStoryChange: ((Int) -> Unit)? = null,
  onComplete: () -> Unit,
  content: @Composable (Int) -> Unit,
) {
  val pagerState = rememberPagerState(initialPage = 0) { numberOfPages }
  val coroutineScope = rememberCoroutineScope()
  var pauseTimer by remember { mutableStateOf(false) }

  LaunchedEffect(pagerState.currentPage) {
    if (pagerState.currentPage == numberOfPages - 1) {
      delay(slideDurationInSeconds * 1000)
    }
  }

  Box(modifier = modifier.fillMaxSize()) {
    StoryImage(
      pagerState = pagerState,
      onTap = { if (touchToPause) pauseTimer = it },
      content = content,
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(modifier = Modifier.padding(spaceBetweenIndicator))

      ListOfIndicators(
        numberOfPages,
        indicatorModifier,
        indicatorBackgroundColor,
        indicatorProgressColor,
        slideDurationInSeconds,
        pauseTimer,
        hideIndicators,
        coroutineScope,
        pagerState,
        spaceBetweenIndicator,
        onEveryStoryChange = onEveryStoryChange,
        onComplete = onComplete,
      )
    }
  }

}

@Composable
private fun RowScope.ListOfIndicators(
  numberOfPages: Int,
  indicatorModifier: Modifier,
  indicatorBackgroundColor: Color,
  indicatorProgressColor: Color,
  slideDurationInSeconds: Long,
  pauseTimer: Boolean,
  hideIndicators: Boolean,
  coroutineScope: CoroutineScope,
  pagerState: PagerState,
  spaceBetweenIndicator: Dp,
  onEveryStoryChange: ((Int) -> Unit)? = null,
  onComplete: () -> Unit,
) {
  var currentPage by remember {
    mutableStateOf(0)
  }

  for (index in 0 until numberOfPages) {
    LinearIndicator(
      modifier = indicatorModifier.weight(1f),
      startProgress = index == currentPage,
      indicatorBackgroundColor,
      indicatorProgressColor,
      slideDurationInSeconds,
      pauseTimer,
      hideIndicators
    ) {
      coroutineScope.launch {
        currentPage++

        if (currentPage < numberOfPages) {
          onEveryStoryChange?.invoke(currentPage)
          pagerState.animateScrollToPage(currentPage)
        }

        if (currentPage == numberOfPages) {
          onComplete()
        }
      }
    }

    Spacer(modifier = Modifier.padding(spaceBetweenIndicator))
  }
}