package com.tiooooo.fintrack.pages.transaction.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.component.textField.CurrencyTextField
import com.tiooooo.fintrack.component.component.textField.StringTextField
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.data.model.transaction.TransactionItem
import com.tiooooo.fintrack.data.model.transaction.TransactionType
import com.tiooooo.fintrack.data.utils.timeNow
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.ToastGravity
import multiplatform.network.cmptoast.showToast

@Composable
fun AddTransactionScreen(
    modifier: Modifier = Modifier,
    screenModel: AddTransactionScreenModel,
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsState()


    LaunchedEffect(Unit) {
        screenModel.effect.collect { effect ->
            when (effect) {
                is AddTransactionEffect.AddTransaction -> {
                    showToast(
                        "Transaksi berhasil ditambahkan",
                        gravity = ToastGravity.Bottom,
                        duration = ToastDuration.Short
                    )
                    navigator.pop()
                }
            }
        }
    }

    BaseScaffold(
        modifier = modifier,
        topBarTitle = "Tambah Transaksi",
        onBackClicked = { navigator.pop() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ) {
            Spacer(modifier = Modifier.height(SMALL_PADDING))

            StringTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = MEDIUM_PADDING),
                value = state.transactionName,
                onValueChange = {
                    screenModel.dispatch(
                        AddTransactionIntent.OnTransactionNameChanged(
                            it
                        )
                    )
                },
                placeHolderText = "Cth: Beli kopi",
                labelText = "Nama Transaksi",
                maxChar = 50
            )

            CurrencyTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = MEDIUM_PADDING),
                value = state.transactionAmount,
                onValueChange = {
                    screenModel.dispatch(
                        AddTransactionIntent.OnTransactionAmountChanged(
                            it
                        )
                    )
                },
                placeHolderText = "Rp 0",
                labelText = "Jumlah",
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = MEDIUM_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                TransactionType.entries.forEach { type ->
                    FilterChip(
                        selected = state.transactionType == type,
                        onClick = {
                            screenModel.dispatch(
                                AddTransactionIntent.OnTransactionTypeChanged(
                                    type
                                )
                            )
                        },
                        label = { Text(type.name) }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                modifier = Modifier.padding(MEDIUM_PADDING)
            ) {
                state.wallets.forEach { option ->
                    ChipExample(
                        label = option.name,
                        isSelected = state.selectedWalletId == option.id.toLong(),
                        onClick = {
                            screenModel.dispatch(AddTransactionIntent.OnWalletChanged(option.id.toLong()))
                        }
                    )
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING),
                onClick = {
                    screenModel.dispatch(
                        AddTransactionIntent.OnTransactionAdded(
                            TransactionItem(
                                name = state.transactionName,
                                amount = if (state.transactionType == TransactionType.EXPENSE) {
                                    state.transactionAmount.toDouble() * -1
                                } else {
                                    state.transactionAmount.toDouble()
                                },
                                type = state.transactionType,
                                walletId = state.selectedWalletId ?: 0,
                                createdAt = timeNow(),
                                id = 0,
                                categoryId = state.selectedCategoryId ?: 0,
                                updatedAt = timeNow(),
                                walletName = "",
                                updatedAtAsString = "",
                            )
                        )
                    )
                }
            ) {
                Text("Simpan")
            }
        }
    }
}

@Composable
fun ChipExample(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(text = label)
        },
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected"
                )
            }
        } else null
    )
}

