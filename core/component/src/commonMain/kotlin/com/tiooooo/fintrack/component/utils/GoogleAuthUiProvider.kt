package com.tiooooo.fintrack.component.utils

import com.tiooooo.fintrack.data.model.auth.AccountInfo

expect class GoogleAuthUiProvider {
   suspend fun signIn(): AccountInfo?
}