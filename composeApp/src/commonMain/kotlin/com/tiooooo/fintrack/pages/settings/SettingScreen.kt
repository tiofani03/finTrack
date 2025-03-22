package com.tiooooo.fintrack.pages.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.pages.detail.DetailRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    settingScreenModel: SettingScreenModel,
) {
    val homeList by settingScreenModel.settingList.collectAsState()
    val listState by settingScreenModel.lazyListState.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val navigator = LocalNavigator.currentOrThrow

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            settingScreenModel.refreshData {
                isRefreshing = false
                CoroutineScope(Dispatchers.Main).launch {
                    listState.scrollToItem(0)
                }
            }
        },
        modifier = modifier
    ) {
        if (isRefreshing){

        }
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
                            navigator.push(DetailRoute(homeList.get(index = it)))
                        }
                        .padding(MEDIUM_PADDING),
                    text = homeList.get(index = it)
                )
            }
        }
    }
}