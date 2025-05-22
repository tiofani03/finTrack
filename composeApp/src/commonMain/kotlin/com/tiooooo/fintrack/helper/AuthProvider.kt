package com.tiooooo.fintrack.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.tiooooo.fintrack.data.utils.GoogleAuthHelper
import com.tiooooo.fintrack.getPlatform
import org.koin.compose.getKoin

val LocalGoogleAuthHelper = staticCompositionLocalOf<GoogleAuthHelper> {
    error("No GoogleAuthHelper provided")
}

@Composable
fun ProvideGoogleAuthHelper(
    content: @Composable () -> Unit
) {
    val koin = getKoin()
    val googleAuthHelper: GoogleAuthHelper = remember {
        if (getPlatform().isAndroid) {
            koin.getScope("activity_scope").get()
        } else {
            koin.get()
        }
    }

    CompositionLocalProvider(
        LocalGoogleAuthHelper provides googleAuthHelper
    ) {
        content()
    }
}
