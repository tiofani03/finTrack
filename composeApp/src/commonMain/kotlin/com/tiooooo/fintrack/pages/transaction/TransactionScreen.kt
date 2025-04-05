package com.tiooooo.fintrack.pages.transaction

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.component.topBar.BasicTopBarTitle
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.pages.detail.DetailRoute
import com.tiooooo.fintrack.pages.transaction.components.CardTransaction
import com.tiooooo.fintrack.pages.transaction.components.CardTransactionDate
import com.tiooooo.fintrack.pages.transaction.components.TransactionFilterBottomSheet
import com.tiooooo.fintrack.pages.transaction.components.TransactionSearchBar
import com.tiooooo.fintrack.pages.transaction.components.calculateTotal
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_transaction_chart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.ToastGravity
import multiplatform.network.cmptoast.showToast
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    transactionScreenModel: TransactionScreenModel,
) {
    val homeList by transactionScreenModel.transactionList.collectAsState()
    val listState by transactionScreenModel.lazyListState.collectAsState()
    var showSheet by remember { mutableStateOf(false) }

    var isRefreshing by remember { mutableStateOf(false) }

    val navigator = LocalNavigator.currentOrThrow
    val groupedTransactions = homeList.groupBy { it.date }

    val scrollOffset = listState.firstVisibleItemIndex
    val scrollOffsetPx = listState.firstVisibleItemScrollOffset

    val isScrolled = scrollOffset > 0 || scrollOffsetPx > 0
    var searchQuery by remember { mutableStateOf("") }

    val offsetY by animateDpAsState(
        targetValue = 0.dp,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )

    Column(
        modifier = modifier
            .animateContentSize(),
    ) {
        AnimatedVisibility(
            visible = !isScrolled,
        ) {
            BasicTopBarTitle(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = MEDIUM_PADDING),
                title = "Transaksi",
                onIconClicked = {},
                iconContent = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_transaction_chart),
                        contentDescription = null,
                    )
                }
            )
        }

        TransactionSearchBar(
            modifier = Modifier.fillMaxWidth().padding(end = MEDIUM_PADDING),
            offsetY = offsetY,
            searchQuery = searchQuery,
            onValueChange = { searchQuery = it },
            onFilterClicked = { showSheet = true }
        )
        PullToRefreshBox(
            modifier = Modifier
                .weight(1f),
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                transactionScreenModel.refreshData {
                    isRefreshing = false
                    CoroutineScope(Dispatchers.Main).launch {
                        listState.scrollToItem(0)
                    }
                }
            },
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                groupedTransactions.forEach { (date, transactions) ->
                    stickyHeader(key = date) {
                        val totalAmount = remember(transactions) { transactions.calculateTotal() }
                        val transactionCount = remember(transactions) { transactions.size }
                        CardTransactionDate(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigator.push(DetailRoute(date))
                                }
                                .background(MaterialTheme.colorScheme.background)
                                .padding(
                                    start = MEDIUM_PADDING,
                                    end = SMALL_PADDING,
                                    top = MEDIUM_PADDING,
                                    bottom = SMALL_PADDING
                                ),
                            date = date,
                            transactionCount = transactionCount,
                            totalAmount = totalAmount,
                        )
                    }

                    itemsIndexed(transactions, key = { _, item -> item.id }) { _, transaction ->
                        CardTransaction(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigator.push(DetailRoute(transaction.date))
                                }
                                .padding(SMALL_PADDING)
                                .padding(bottom = SMALL_PADDING),
                            transactionItem = transaction,
                            onTransactionClicked = {
                                navigator.push(DetailRoute(it.date))
                            }
                        )
                    }
                }
            }

        }
    }

    if (showSheet) {
        TransactionFilterBottomSheet(
            onClick = { text ->
                showToast(
                    message = "This is Short Toast $text",
                    gravity = ToastGravity.Top,
                    duration = ToastDuration.Short
                )
                showSheet = false
            }
        ) {
            showSheet = false
        }
    }
}