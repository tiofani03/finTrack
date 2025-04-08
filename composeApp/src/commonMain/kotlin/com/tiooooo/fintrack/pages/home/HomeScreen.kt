package com.tiooooo.fintrack.pages.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.pages.home.components.SectionCardTotal
import com.tiooooo.fintrack.pages.home.components.SectionCashFlow
import com.tiooooo.fintrack.pages.home.components.SectionLatestTransaction
import com.tiooooo.fintrack.pages.home.components.SectionLogo
import com.tiooooo.fintrack.pages.home.components.SectionTextGreeting
import com.tiooooo.fintrack.pages.home.components.SummarySection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenModel: HomeScreenModel,
    onTransactionClicked: () -> Unit,
) {
    val latestTransactionList by homeScreenModel.latestTransactionList.collectAsState()
    val listState by homeScreenModel.lazyListState.collectAsState()
    val summaryListState by homeScreenModel.summaryListState.collectAsState()
    val summaryList by homeScreenModel.summaryList.collectAsState()
    val walletAmount by homeScreenModel.walletAmountTotal.collectAsState()

    val scrollOffset = listState.firstVisibleItemIndex
    val scrollOffsetPx = listState.firstVisibleItemScrollOffset
    val isScrolled = scrollOffset > 0 || scrollOffsetPx > 0

    Column(modifier = modifier) {
        AnimatedVisibility(
            visible = !isScrolled
        ) {
            SectionTextGreeting(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = MEDIUM_PADDING, start = MEDIUM_PADDING),
                name = "Tio",
            )
        }
        SectionLogo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
            isScrolled = isScrolled
        )
        LazyColumn(
            modifier = Modifier,
            state = listState,
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
            contentPadding = PaddingValues(vertical = MEDIUM_PADDING),
        ) {
            item {
                SectionCardTotal(
                    modifier = Modifier
                        .padding(horizontal = MEDIUM_PADDING)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    walletAmount = walletAmount,
                    onTransactionClicked = onTransactionClicked,
                )
            }
            item {
                SummarySection(
                    modifier = Modifier.fillMaxWidth(),
                    summaryList = summaryList,
                    summaryListState = summaryListState,
                )
            }
            item {
                SectionLatestTransaction(
                    modifier = Modifier.padding(horizontal = MEDIUM_PADDING),
                    transactionItem = latestTransactionList,
                )
            }
            item {
                SectionCashFlow(
                    modifier = Modifier
                        .padding(horizontal = MEDIUM_PADDING)
                )
            }
        }
    }
    homeScreenModel.updateState(listState)
    homeScreenModel.updateSummaryList(summaryListState)
}

