package com.tiooooo.fintrack.auth.pages.onboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiooooo.fintrack.auth.pages.onboard.components.AutoPlayPager
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.component.button.ButtonPrimary
import com.tiooooo.fintrack.component.component.button.ButtonSecondary
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.buttonPrimaryColor
import com.tiooooo.fintrack.component.theme.primaryDark
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
    val state by onboardScreenModel.state.collectAsStateWithLifecycle()

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
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(500)
        isVisibleBottomSheet = true
    }

    BaseScaffold(
        modifier = modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            AutoPlayPager(
                modifier = Modifier
                    .fillMaxHeight(0.85f)
                    .padding(top = paddingValues.calculateTopPadding())
                    .fillMaxWidth()
                    .padding(bottom = MEDIUM_PADDING),
                pageCount = state.screenItems.size,
                indicatorColor = buttonPrimaryColor
            ) { index ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier
                            .fillMaxHeight(0.55f)
                            .align(Alignment.Center)
                            .padding(horizontal = EXTRA_LARGE_PADDING)
                            .padding(bottom = EXTRA_LARGE_PADDING),
                        painter = painterResource(state.screenItems[index].imageRes),
                        contentDescription = null,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = MEDIUM_PADDING)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = MEDIUM_PADDING),
                            text = state.screenItems[index].title,
                            style = textMedium20().copy(
//                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(
                                    top = SMALL_PADDING,
                                    start = MEDIUM_PADDING,
                                    end = MEDIUM_PADDING,
                                ),
                            text = state.screenItems[index].description,
                            style = textMedium14().copy(
//                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Center,
                            ),

                            )
                    }
                }
            }

            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                visible = isVisibleBottomSheet,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight },
                    animationSpec = tween(durationMillis = 500, easing = EaseOutCubic)
                ) + fadeIn(animationSpec = tween(500))
            ) {
                ButtonPrimary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MEDIUM_PADDING,
                            end = MEDIUM_PADDING,
                            bottom = MEDIUM_PADDING,
                        ),
                    text = "Get Started",
                    onClick = {
                        nav.navigate("/auth/login")
                    }
                )
            }
        }
    }
}