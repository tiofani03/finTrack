package com.tiooooo.fintrack.adaptor

interface AuthService {
    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit)
}