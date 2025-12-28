package com.tiooooo.fintrack.auth.pages.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tiooooo.fintrack.auth.pages.login.LoginActions
import com.tiooooo.fintrack.auth.pages.login.LoginIntent
import com.tiooooo.fintrack.auth.pages.login.LoginScreenModel
import com.tiooooo.fintrack.component.base.BaseScaffold
import com.tiooooo.fintrack.component.theme.EXTRA_SMALL_PADDING
import com.tiooooo.fintrack.component.theme.HUGE_PADDING
import com.tiooooo.fintrack.component.theme.LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.primaryDark
import com.tiooooo.fintrack.component.theme.primaryLight
import com.tiooooo.fintrack.component.theme.textMedium30
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(
                        Brush.verticalGradient(
                            listOf(primaryDark, primaryLight)
                        )
                    )
            )
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = MEDIUM_PADDING)
                    .align(Alignment.Center)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "Welcome Back",
                    color = Color.White,
                    style = textMedium30().copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = EXTRA_SMALL_PADDING),
                    text = "Log in to track your wealth",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
                LoginCard(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = HUGE_PADDING),
                    loginState = state,
                    loginActions = LoginActions(
                        onNavigate = { path -> nav.navigate(path) },
                        onEmailChanged = { email ->
                            loginScreenModel.dispatch(LoginIntent.SetEmail(email))
                        },
                        onPasswordChanged = { password ->
                            loginScreenModel.dispatch(LoginIntent.SetPassword(password))
                        }
                    )
                )
                LoginOrRegisterText(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = LARGE_PADDING),
                ) {
                    nav.navigate("auth/register")
                }
            }
        }
    }
}