package com.tiooooo.fintrack.pages.onboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tiooooo.fintrack.component.theme.EXTRA_LARGE_PADDING
import com.tiooooo.fintrack.component.theme.MEDIUM_PADDING
import com.tiooooo.fintrack.component.theme.SMALL_PADDING
import com.tiooooo.fintrack.component.theme.textMedium18
import com.tiooooo.fintrack.component.utils.GoogleAuthUiProvider
import com.tiooooo.fintrack.pages.dashboard.DashboardRoute
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_login_apple
import fintrack.composeapp.generated.resources.ic_login_facebook
import fintrack.composeapp.generated.resources.ic_login_google
import kotlinx.coroutines.launch
import multiplatform.network.cmptoast.showToast
import org.koin.compose.getKoin

@Composable
fun OnboardBottomSheet(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    val googleAuthUiProvider: GoogleAuthUiProvider = getKoin().get()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = EXTRA_LARGE_PADDING),
            text = "Login or signup with",
            style = textMedium18().copy(
                fontWeight = FontWeight.Bold
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MEDIUM_PADDING
                )
                .padding(top = SMALL_PADDING),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            OnboardButton(
                painter = Res.drawable.ic_login_google,
                onCardClicked = {
//                    authService.signIn("test@gmail.com", "123456") { success, err ->
//                    authService.signInWithGoogle { success, err ->
//                        println("helloworld: $success $err")
//                        if (success) {
//                            navigator.replaceAll(DashboardRoute)
//                        } else {
//                            showToast(err.toString())
//                        }
//                    }
                    coroutineScope.launch {
                        val user = googleAuthUiProvider.signIn()
                        showToast(user.toString())
                    }
                }
            )
            OnboardButton(
                painter = Res.drawable.ic_login_apple,
                onCardClicked = {

                }
            )
            OnboardButton(
                painter = Res.drawable.ic_login_facebook,
                onCardClicked = {
                    navigator.replaceAll(DashboardRoute)
                }
            )
        }
    }
}