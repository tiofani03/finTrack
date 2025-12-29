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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.tiooooo.fintrack.navigation.path.NavPath.AUTH_ONBOARD
import com.tiooooo.fintrack.navigation.rememberNavHelper
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashScreenModel: SplashScreenModel,
) {
    val navigator = rememberNavHelper()
    var step by remember { mutableIntStateOf(0) }
    val enterAnim = remember {
        fadeIn(
            animationSpec = tween(600, easing = EaseOutCubic)
        ) + slideInVertically(
            initialOffsetY = { it / 4 },
            animationSpec = tween(600, easing = EaseIn)
        )
    }

    LaunchedEffect(Unit) {
        delay(400)
        step = 1
        delay(300)
        step = 2
        delay(250)
        step = 3
        delay(800)
    }
    LaunchedEffect(splashScreenModel) {
        splashScreenModel.effect.collectLatest { effect ->
            when (effect) {
                SplashEffect.NavigateToOnboard ->
                    navigator.replaceAll(AUTH_ONBOARD)

                SplashEffect.NavigateToDashboard ->
                    navigator.replaceAll("/dashboard")
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
                    .padding(bottom = MEDIUM_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedVisibility(
                    visible = step >= 1,
                    enter = enterAnim
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

                AnimatedVisibility(visible = step >= 2) {
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

                AnimatedVisibility(visible = step >= 3) {
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

