package com.tiooooo.fintrack.auth.pages.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.tiooooo.fintrack.auth.pages.login.components.LoginOrRegisterText
import com.tiooooo.fintrack.auth.pages.register.RegisterActions
import com.tiooooo.fintrack.auth.pages.register.RegisterIntent
import com.tiooooo.fintrack.auth.pages.register.RegisterScreenModel
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
fun RegisterContent(
    modifier: Modifier = Modifier,
    registerScreenModel: RegisterScreenModel,
) {
    val nav = rememberNavHelper()
    val state by registerScreenModel.state.collectAsStateWithLifecycle()

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
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(horizontal = MEDIUM_PADDING)
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "Track your Wealth Simply",
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
                    text = "Start your journey to financial freedom today",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
                RegisterCard(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = HUGE_PADDING),
                    registerState = state,
                    registerActions = RegisterActions(
                        onNavigate = { path -> nav.navigate(path) },
                        onNameChanged = { name ->
                            registerScreenModel.dispatch(RegisterIntent.SetName(name))
                        },
                        onEmailChanged = { email ->
                            registerScreenModel.dispatch(RegisterIntent.SetEmail(email))
                        },
                        onPasswordChanged = { password ->
                            registerScreenModel.dispatch(RegisterIntent.SetPassword(password))
                        },
                        onConfirmPasswordChanged ={ confirmPassword ->
                            registerScreenModel.dispatch(RegisterIntent.SetConfirmPassword(confirmPassword))
                        },
                    )
                )
                LoginOrRegisterText(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = LARGE_PADDING),
                    firstText = "Already have an account? ",
                    highlightedText = "Log In",
                ) {
                    nav.pop()
                }
            }
        }
    }
}