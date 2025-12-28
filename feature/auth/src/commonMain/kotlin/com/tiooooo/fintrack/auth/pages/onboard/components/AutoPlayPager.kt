package com.tiooooo.fintrack.auth.pages.onboard.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AutoPlayPager(
    modifier: Modifier = Modifier,
    pageCount: Int,
    autoPlayDelayMillis: Long = 5000,
    indicatorSpacing: Dp = 8.dp,
    activeIndicatorWidth: Dp = 24.dp,
    inactiveIndicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = 8.dp,
    indicatorColor: Color = Color.White,
    inactiveIndicatorColor: Color = Color.LightGray,
    content: @Composable (Int) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pageCount }
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(autoPlayDelayMillis)

            val nextPage =
                if (pagerState.currentPage == pageCount - 1) 0
                else pagerState.currentPage + 1

            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = true // bisa digeser
        ) { page ->
            content(page)
        }

        Spacer(modifier = Modifier.height(12.dp))

        PagerIndicator(
            pageCount = pageCount,
            currentPage = pagerState.currentPage,
            spacing = indicatorSpacing,
            activeWidth = activeIndicatorWidth,
            inactiveWidth = inactiveIndicatorWidth,
            height = indicatorHeight,
            activeColor = indicatorColor,
            inactiveColor = inactiveIndicatorColor
        )
    }
}

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    spacing: Dp,
    activeWidth: Dp,
    inactiveWidth: Dp,
    height: Dp,
    activeColor: Color,
    inactiveColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage

            val width by animateDpAsState(
                targetValue = if (isSelected) activeWidth else inactiveWidth,
                animationSpec = tween(300),
                label = "indicatorWidth"
            )

            Box(
                modifier = Modifier
                    .height(height)
                    .width(width)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (isSelected) activeColor else inactiveColor
                    )
            )
        }
    }
}

