package com.tiooooo.fintrack.pages.onboard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun LinearIndicator(
    modifier: Modifier,
    startProgress: Boolean = false,
    indicatorBackgroundColor: Color,
    indicatorProgressColor: Color,
    slideDurationInSeconds: Long,
    onPauseTimer: Boolean = false,
    hideIndicators: Boolean = false,
    onAnimationEnd: () -> Unit
) {

    val delayInMillis = rememberSaveable {
        (slideDurationInSeconds * 1000) / 100
    }

    var progress by remember { mutableStateOf(0.00f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )


    if (startProgress) {
        LaunchedEffect(key1 = onPauseTimer) {
            while (progress < 1f && isActive && onPauseTimer.not()) {
                progress += 0.01f
                delay(delayInMillis)
            }

            if (onPauseTimer.not()) {
                delay(200)
                onAnimationEnd()
            }
        }
    }

    if (hideIndicators.not()) {
        LinearProgressIndicator(
            backgroundColor = indicatorBackgroundColor,
            color = indicatorProgressColor,
            modifier = modifier
                .clip(RoundedCornerShape(12.dp)),
            progress = animatedProgress
        )
    }
}