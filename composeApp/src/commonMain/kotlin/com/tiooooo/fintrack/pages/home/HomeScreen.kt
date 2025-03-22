package com.tiooooo.fintrack.pages.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.tiooooo.fintrack.component.theme.SMALL_PADDING

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenModel: HomeScreenModel,
) {
    val homeList by homeScreenModel.dashboardList.collectAsState()
    val listState by homeScreenModel.lazyListState.collectAsState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(homeList.size) {
            Text(
                modifier = Modifier.padding(SMALL_PADDING),
                text = homeList.get(index = it)
            )
        }
    }

    homeScreenModel.updateState(listState)
}