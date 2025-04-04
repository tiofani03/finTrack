package com.tiooooo.fintrack.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tiooooo.fintrack.component.component.topBar.CommonTopBar
import com.tiooooo.fintrack.component.theme.onLightBackground

@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier.fillMaxSize(),
    topBarTitle: String = "",
    topBarEnable: Boolean = false,
    onBackClicked: (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable (padding: PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (topBarTitle.isNotEmpty() || topBarEnable) {
                CommonTopBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = topBarTitle,
                    onBackClicked = {
                        onBackClicked?.invoke()
                    }
                )
            }
        },
        bottomBar = {
            bottomBar?.invoke()
        }
    ) { padding ->
        content(padding)
    }
}