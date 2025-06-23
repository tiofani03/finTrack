package com.tiooooo.fintrack.auth.pages.onboard.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun StoryImage(
  pagerState: PagerState,
  onTap: (Boolean) -> Unit,
  content: @Composable (Int) -> Unit,
) {
  HorizontalPager(
    state = pagerState,
    userScrollEnabled = false,
    modifier = Modifier.pointerInput(Unit) {
      detectTapGestures(
        onPress = {
          onTap(true)
          try {
            awaitRelease()
          } finally {
            onTap(false)
          }
        }
      )
    }) { pageIndex ->
    content(pageIndex)
  }
}
