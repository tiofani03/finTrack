package com.tiooooo.fintrack.pages.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    val state by homeScreenModel.state.collectAsState()
    val scrollOffset = state.listState.firstVisibleItemIndex
    val scrollOffsetPx = state.listState.firstVisibleItemScrollOffset
    val isScrolled = scrollOffset > 0 || scrollOffsetPx > 0

    LaunchedEffect(Unit) {
        homeScreenModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToTransaction -> onTransactionClicked()
                is HomeEffect.ShowToast -> {}
            }
        }
    }


    LaunchedEffect(state.listState) {
        homeScreenModel.dispatch(HomeIntent.UpdateListState(state = state.listState))
    }

    LaunchedEffect(state.summaryListState) {
        homeScreenModel.dispatch(HomeIntent.UpdateSummaryState(state = state.summaryListState))
    }

    Column(modifier = modifier) {
        AnimatedVisibility(
            visible = !isScrolled
        ) {
            SectionTextGreeting(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = MEDIUM_PADDING, start = MEDIUM_PADDING),
                name = "User", // TODO: Replace with actual user name
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
            state = state.listState,
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
            contentPadding = PaddingValues(vertical = MEDIUM_PADDING),
        ) {
            item {
                SectionCardTotal(
                    modifier = Modifier
                        .padding(horizontal = MEDIUM_PADDING)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    walletAmount = state.totalAmount,
                    onTransactionClicked = {
                        homeScreenModel.dispatch(HomeIntent.OnTransactionClicked)
                    },
                )
            }
            item {
                SummarySection(
                    modifier = Modifier.fillMaxWidth(),
                    summaryList = state.summary,
                    summaryListState = state.summaryListState,
                )
            }
            item {
                SectionLatestTransaction(
                    modifier = Modifier.padding(horizontal = MEDIUM_PADDING),
                    transactionItem = state.transactions,
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
}

