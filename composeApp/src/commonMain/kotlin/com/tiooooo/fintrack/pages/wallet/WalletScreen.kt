package com.tiooooo.fintrack.pages.wallet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium12
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium22
import com.tiooooo.fintrack.pages.transaction.TransactionScreenModel
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_onboard_sort
import fintrack.composeapp.generated.resources.ic_transaction_chart
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingEffectScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    transactionScreenModel: TransactionScreenModel
) {
    val listState = rememberLazyListState()

    // Kondisi ketika scroll mencapai bawah (dalam hal ini, lebih dari 0)
    val scrollOffset = listState.firstVisibleItemIndex
    val scrollOffsetPx = listState.firstVisibleItemScrollOffset

    val isScrolled = scrollOffset > 0 || scrollOffsetPx > 0

    val offsetY by animateDpAsState(
        targetValue = 0.dp,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        ) // Durasi animasi lebih halus
    )

    Column(
        modifier = modifier,
    ) {
        // Box yang akan hilang ketika scroll ke bawah
        AnimatedVisibility(
            visible = !isScrolled,
            enter = fadeIn(tween(300)), // Durasi fade-in
            exit = fadeOut(tween(300)) // Durasi fade-out
        ) {
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.13f)
                        .background(Color.Transparent),
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = MEDIUM_PADDING),
                    textAlign = TextAlign.Start,
                    text = "Transaksi",
                    style = textMedium22().copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.CenterEnd).padding(
                        top = MEDIUM_PADDING,
                    ),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_transaction_chart),
                        contentDescription = null,
                    )
                }
            }
        }

        // TextField yang bergerak ke atas dengan animasi yang lebih halus
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = MEDIUM_PADDING)
                    .offset(y = offsetY),
                value = "",
                textStyle = textMedium12(),
                onValueChange = {

                },
                shape = RoundedCornerShape(MEDIUM_PADDING),
                placeholder = {
                    Text(
                        text = "Cari Transkasi",
                        style = textMedium12(),
                        maxLines = 1,
                    )
                },
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Gray,
                    selectionColors = TextSelectionColors(
                        backgroundColor = Color.Transparent,
                        handleColor = Color.Transparent,
                    ),
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                trailingIcon = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                    )
                },
            )
            // Memberikan lebar tetap untuk IconButton, alihkan dari weight
            IconButton(
                modifier = Modifier
                    .width(48.dp)  // Lebar yang lebih terkontrol untuk ikon
                    .height(48.dp) // Pastikan tinggi konsisten
                    .align(Alignment.CenterVertically),
                onClick = {},
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_onboard_sort),
                    contentDescription = null,
                    modifier = Modifier.padding(SMALL_PADDING),
                )
            }
        }




        PullToRefreshBox(
            modifier = Modifier
                .weight(1f)
                .padding(top = SMALL_PADDING),
            isRefreshing = isRefreshing,
            onRefresh = {
                transactionScreenModel.refreshData {
                    // Perform any additional actions on refresh
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                // Your LazyColumn content here
                items(100) { index -> // Replace with your actual data
                    Text(
                        text = "Item $index",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

