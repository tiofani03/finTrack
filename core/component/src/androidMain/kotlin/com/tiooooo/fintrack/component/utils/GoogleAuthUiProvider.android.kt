package com.tiooooo.fintrack.component.utils

import com.tiooooo.fintrack.data.model.auth.AccountInfo

actual class GoogleAuthUiProvider {
    actual suspend fun signIn(): AccountInfo? {
        return null
    }
}