package com.tiooooo.fintrack.data.model.auth

data class AccountInfo(
    val token: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)