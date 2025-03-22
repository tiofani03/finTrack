package com.tiooooo.fintrack.pages.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium22
import com.tiooooo.fintrack.pages.detail.DetailRoute
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_transaction_chart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    transactionScreenModel: TransactionScreenModel,
) {
    val homeList by transactionScreenModel.transactionList.collectAsState()
    val listState by transactionScreenModel.lazyListState.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val navigator = LocalNavigator.currentOrThrow

    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.wrapContentSize()
        ) {
            Spacer(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.13f)
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
                    end = MEDIUM_PADDING,
                ),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_transaction_chart),
                    contentDescription = null,
                )
            }
        }
        PullToRefreshBox(
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
            modifier = Modifier
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                items(homeList.size) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SMALL_PADDING)
                            .clickable {
                                navigator.push(DetailRoute(homeList.get(index = it).id.toString()))
                            }
                            .padding(MEDIUM_PADDING),
                        text = homeList.get(index = it).purpose
                    )
                }
            }
        }
    }
}