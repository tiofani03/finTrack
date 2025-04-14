package com.tiooooo.fintrack.pages.wallet

import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.FinTrackAppColors
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium14
import com.tiooooo.fintrack.component.theme.textMedium20
import com.tiooooo.fintrack.component.utils.formatRupiah
import com.tiooooo.fintrack.pages.detail.DetailRoute
import com.tiooooo.fintrack.pages.detail.DetailScreen
import com.tiooooo.fintrack.pages.home.HomeEffect
import com.tiooooo.fintrack.pages.wallet.components.WalletCardAddItem
import com.tiooooo.fintrack.pages.wallet.components.WalletCardItem

@Composable
fun WalletScreen(
    modifier: Modifier = Modifier,
    walletScreenModel: WalletScreenModel,
) {
    val state by walletScreenModel.state.collectAsState()
    val appColors = FinTrackAppColors.current
    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(Unit) {
        walletScreenModel.effect.collect { effect ->
            when (effect) {
                is WalletEffect.NavigateToAddWallet -> {
                    navigator.push(DetailRoute("Hai"))
                }
                else -> Unit
            }
        }
    }

    BaseScaffold(
        modifier = modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            appColors.backgroundPrimaryLightColor,
                            appColors.backgroundPrimaryDarkColor,
                        )
                    )
                )
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
                        color = appColors.textWhiteColor,
                    ),
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SMALL_PADDING)
                        .padding(horizontal = SMALL_PADDING),
                    text = formatRupiah(amount = state.totalAmount),
                    style = textMedium20().copy(
                        fontWeight = FontWeight.Bold,
                        color = appColors.textWhiteColor,
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
                    state = state.listState,
                    contentPadding = PaddingValues(
                        top = SMALL_PADDING,
                        bottom = paddingValues.calculateBottomPadding(),
                    )
                ) {
                    items(state.wallets.size) { pos ->
                        WalletCardItem(
                            modifier = Modifier
                                .padding(EXTRA_SMALL_PADDING),
                            walletItem = state.wallets[pos]
                        )
                    }
                    item {
                        WalletCardAddItem(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(EXTRA_SMALL_PADDING),
                            onAddWalletClicked = { walletScreenModel.dispatch(WalletIntent.UpdateAmount(5000.0)) }
                        )
                    }
                }
            }
        }
    }
}

