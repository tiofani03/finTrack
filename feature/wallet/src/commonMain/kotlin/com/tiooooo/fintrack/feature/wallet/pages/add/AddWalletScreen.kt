package com.tiooooo.fintrack.feature.wallet.pages.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.component.textField.CurrencyTextField
import com.tiooooo.fintrack.component.component.textField.StringTextField
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.data.local.entity.WalletEntity
import com.tiooooo.fintrack.data.model.wallet.createDefaultWalletItem
import com.tiooooo.fintrack.data.utils.timeNow
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletEffect.AddWallet
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletAdded
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletAmountChanged
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletColorChanged
import com.tiooooo.fintrack.feature.wallet.pages.add.AddWalletIntent.OnWalletNameChanged
import com.tiooooo.fintrack.feature.wallet.pages.components.WalletCardItem
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.ToastGravity
import multiplatform.network.cmptoast.showToast

@Composable
fun AddWalletScreen(
    modifier: Modifier = Modifier,
    screenModel: AddWalletScreenModel,
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsState()

    var walletItem by remember { mutableStateOf(createDefaultWalletItem().copy(color = state.walletColor)) }
    LaunchedEffect(Unit) {
        screenModel.effect.collect {
            when (it) {
                is AddWallet -> {
                    showToast(
                        message = "Dompet berhasil ditambahkan",
                        gravity = ToastGravity.Bottom,
                        duration = ToastDuration.Short
                    )
                    navigator.pop()
                }
            }
        }
    }

    LaunchedEffect(state.walletColor, state.walletName, state.walletAmount) {
        walletItem = walletItem.copy(
            color = state.walletColor,
            name = state.walletName,
            amountDouble = state.walletAmount.toDoubleOrNull() ?: 0.0,
            amount = state.walletAmount,
        )
    }

    BaseScaffold(
        modifier = modifier,
        topBarTitle = "Atur Dompet",
        onBackClicked = {
            navigator.pop()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                WalletCardItem(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally),
                    walletItem = walletItem,
                )
                Spacer(modifier.height(MEDIUM_PADDING))

                LazyRow(
                    modifier = Modifier
                        .height(70.dp)
                        .padding(bottom = MEDIUM_PADDING)
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                    contentPadding = PaddingValues(horizontal = MEDIUM_PADDING)
                ) {
                    items(state.colorOptions) { color ->
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = 2.dp,
                                    color = if (color == state.walletColor) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    screenModel.dispatch(OnWalletColorChanged(color))
                                }
                        )
                    }
                }

                StringTextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = MEDIUM_PADDING),
                    value = state.walletName,
                    onValueChange = { screenModel.dispatch(OnWalletNameChanged(it)) },
                    placeHolderText = "Cth: Cash",
                    labelText = "Nama Dompet",
                    maxChar = 20
                )

                CurrencyTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = SMALL_PADDING)
                        .padding(horizontal = MEDIUM_PADDING),
                    value = state.walletAmount,
                    onValueChange = { screenModel.dispatch(OnWalletAmountChanged(it)) },
                    placeHolderText = "Rp 0",
                    labelText = "Saldo Saat ini",
                )
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(MEDIUM_PADDING),
                onClick = {
                    screenModel.dispatch(
                        OnWalletAdded(
                            WalletEntity(
                                name = state.walletName,
                                balance = state.walletAmount.toDoubleOrNull() ?: 0.0,
                                color = state.walletColor.toArgb(),
                                currency = state.walletCurrency,
                                createdAt = timeNow(),
                                updatedAt = timeNow(),
                            )
                        )
                    )
                },
            ) {
                Text("Simpan")
            }
        }
    }
}
