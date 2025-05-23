package com.tiooooo.fintrack.data.utils

import com.tiooooo.fintrack.data.model.auth.AccountInfo

expect class GoogleAuthHelper {
    suspend fun signIn(): AccountInfo?
    fun signOut()
    fun getAccountInfo(): AccountInfo?
}