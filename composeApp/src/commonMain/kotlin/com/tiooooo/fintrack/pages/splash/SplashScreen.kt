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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashScreenModel: SplashScreenModel,
) {
    var isVisibleLogo by remember { mutableStateOf(false) }
    var isVisibleTitle by remember { mutableStateOf(false) }
    var isVisibleSubtitle by remember { mutableStateOf(false) }

    val fadeInSpec = fadeIn(animationSpec = tween(durationMillis = 600, easing = EaseOutCubic))
    val slideInSpec = slideInVertically(
        initialOffsetY = { fullHeight -> fullHeight / 4 },
        animationSpec = tween(durationMillis = 600, easing = EaseIn)
    )
    LaunchedEffect(Unit) {
        delay(400)
        isVisibleLogo = true
        delay(300)
        isVisibleTitle = true
        delay(250)
        isVisibleSubtitle = true
        delay(splashScreenModel.delayTime)
        splashScreenModel.navigateToOnboardPage()
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
                AnimatedVisibility(
                    visible = isVisibleLogo,
                    enter = fadeInSpec + slideInSpec
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = EXTRA_LARGE_PADDING),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )
                }

                AnimatedVisibility(
                    visible = isVisibleTitle,
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(
                                vertical = SMALL_PADDING,
                                horizontal = EXTRA_LARGE_PADDING,
                            )
                            .align(Alignment.CenterHorizontally),
                        text = "FinTrack",
                        style = textMedium20().copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                AnimatedVisibility(
                    visible = isVisibleSubtitle,
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = EXTRA_LARGE_PADDING)
                            .align(Alignment.CenterHorizontally),
                        text = "Gak Cuma Gaya, Keuangan Juga Harus Keren",
                        style = textMedium12().copy(
                            fontWeight = FontWeight.Light,
                            color = Color.White
                        )
                    )
                }
            }

            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = paddingValues.calculateBottomPadding() + MEDIUM_PADDING)
                    .align(Alignment.BottomCenter),
                text = "Versi 1.0.0",
                style = textMedium12().copy(
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            )
        }
    }
}
