package com.tiooooo.fintrack.pages.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.primaryDark
import com.tiooooo.fintrack.component.theme.primaryLight
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium20
import com.tiooooo.fintrack.component.utils.formatRupiah
import com.tiooooo.fintrack.pages.wallet.components.WalletCardAddItem
import com.tiooooo.fintrack.pages.wallet.components.WalletCardItem

@Composable
fun WalletScreen(
    modifier: Modifier = Modifier,
    walletScreenModel: WalletScreenModel,
) {
    val walletList by walletScreenModel.walletList.collectAsState()
    val listState by walletScreenModel.lazyListState.collectAsState()
    val walletAmountTotal by walletScreenModel.walletAmountTotal.collectAsState()

    BaseScaffold(
        modifier = modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(primaryDark, primaryLight)))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .padding(top = paddingValues.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SMALL_PADDING),
                    text = "Keuangan Saya",
                    style = textMedium14().copy(
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    ),
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SMALL_PADDING)
                        .padding(horizontal = SMALL_PADDING),
                    text = formatRupiah(amount = walletAmountTotal),
                    style = textMedium20().copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    textAlign = TextAlign.Center,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .align(Alignment.BottomCenter)
                    .background(
                        shape = RoundedCornerShape(
                            topStart = EXTRA_LARGE_PADDING,
                            topEnd = EXTRA_LARGE_PADDING,
                        ),
                        color = MaterialTheme.colorScheme.background,
                    ),
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                        .padding(top = SMALL_PADDING),
                    columns = GridCells.Fixed(2),
                    state = listState,
                    contentPadding = PaddingValues(
                        top = SMALL_PADDING,
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                ) {
                    items(walletList.size) { pos ->
                        WalletCardItem(
                            modifier = Modifier
                                .padding(EXTRA_SMALL_PADDING),
                            walletItem = walletList[pos]
                        )
                    }
                    item {
                        WalletCardAddItem(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(EXTRA_SMALL_PADDING),
                        )
                    }
                }
            }
        }
    }

    walletScreenModel.updateState(listState)
}

