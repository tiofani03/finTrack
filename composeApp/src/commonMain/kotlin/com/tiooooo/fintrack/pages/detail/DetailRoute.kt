package com.tiooooo.fintrack.pages.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

data class DetailRoute(
    val text: String
) : Screen {

    @Composable
    override fun Content() {
        val detailScreenModel = rememberScreenModel { DetailScreenModel() }
        DetailScreen(
            modifier = Modifier.fillMaxSize(),
            detailScreenModel = detailScreenModel,
            itemId = text,
        )
    }
}