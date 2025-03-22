package com.tiooooo.fintrack.pages.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.utils.onError
import com.tiooooo.fintrack.component.utils.onLoading
import com.tiooooo.fintrack.component.utils.onSuccess
import com.tiooooo.fintrack.pages.dashboard.DashboardRoute

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    itemId: String,
    detailScreenModel: DetailScreenModel,
) {
    LaunchedEffect(itemId) {
        detailScreenModel.loadItemData(itemId)
    }

    val uiState by detailScreenModel.uiState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    BaseScaffold(
        modifier = modifier,
        topBarTitle = itemId,
        onBackClicked = {
            navigator.replaceAll(DashboardRoute)
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            uiState.apply {
                onLoading {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                            .padding(it),
                    )
                }
                onSuccess { data ->
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = data,
                    )
                }
                onError { errorMessage ->
                    Text(text = errorMessage, color = Color.Red)
                }
            }
        }
    }
}