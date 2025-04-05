package com.tiooooo.fintrack.pages.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.primaryDark
import com.tiooooo.fintrack.component.theme.primaryLight
import com.tiooooo.fintrack.component.theme.textMedium10
import com.tiooooo.fintrack.component.theme.textMedium16
import com.tiooooo.fintrack.component.theme.textMedium18
import com.tiooooo.fintrack.pages.wallet.components.WalletCardItem
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenModel: HomeScreenModel,
) {
    val homeList by homeScreenModel.dashboardList.collectAsState()
    val listState by homeScreenModel.lazyListState.collectAsState()
    val walletList by homeScreenModel.walletList.collectAsState()

    val scrollOffset = listState.firstVisibleItemIndex
    val scrollOffsetPx = listState.firstVisibleItemScrollOffset
    val isScrolled = scrollOffset > 0 || scrollOffsetPx > 0

    Column(
        modifier = modifier,
    ) {
        AnimatedVisibility(
            visible = !isScrolled
        ){
            Row {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MEDIUM_PADDING, start = MEDIUM_PADDING),
                    text = "Selamat siang Tiooooo",
                    style = textMedium10().copy(
                        fontWeight = FontWeight.ExtraLight
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .size(HUGE_PADDING),
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "FinTrack",
                style = textMedium18().copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.weight(1f))

            BadgedBox(
                badge = {
                    Badge()
                }
            ) {
                Icon(
                    modifier = Modifier.size(EXTRA_LARGE_PADDING),
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifikasi"
                )
            }
        }

        LazyColumn(
            modifier = Modifier,
            state = listState
        ) {
            item{
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    contentPadding = PaddingValues(SMALL_PADDING),
                    horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
                ) {
                    items(walletList.size) { pos ->
                        WalletCardItem(
                            modifier = Modifier
                                .padding(vertical = EXTRA_SMALL_PADDING), // padding atas-bawah kalau perlu
                            walletItem = walletList[pos]
                        )
                    }
                }

            }
            items(homeList.size) {
                Text(
                    modifier = Modifier.padding(SMALL_PADDING),
                    text = homeList.get(index = it)
                )
            }
        }
    }
    homeScreenModel.updateState(listState)
}

