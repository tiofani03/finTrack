package com.tiooooo.fintrack.pages.onboard

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.primaryDark
import com.tiooooo.fintrack.component.theme.primaryLight
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium20
import com.tiooooo.fintrack.pages.onboard.components.OnBoardStories
import com.tiooooo.fintrack.pages.onboard.components.OnboardBottomSheet
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardScreen(
    modifier: Modifier = Modifier,
    onboardScreenModel: OnboardScreenModel,
) {
    val listOfImages = onboardScreenModel.listOfImages
    val listOfText = onboardScreenModel.listOfText

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

            OnboardBottomSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .align(Alignment.BottomCenter)
                    .background(
                        shape = RoundedCornerShape(
                            topStart = EXTRA_LARGE_PADDING,
                            topEnd = EXTRA_LARGE_PADDING,
                        ),
                        color = MaterialTheme.colorScheme.background,
                    ),
            )
        }
    }
}
