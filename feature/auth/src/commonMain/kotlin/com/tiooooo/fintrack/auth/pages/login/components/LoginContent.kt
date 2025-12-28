package com.tiooooo.fintrack.auth.pages.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiooooo.fintrack.auth.pages.login.LoginScreenModel
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.navigation.rememberNavHelper

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    loginScreenModel: LoginScreenModel,
) {
    val nav = rememberNavHelper()
    val state by loginScreenModel.state.collectAsStateWithLifecycle()

    BaseScaffold(
        modifier = modifier,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {

        }
    }
}