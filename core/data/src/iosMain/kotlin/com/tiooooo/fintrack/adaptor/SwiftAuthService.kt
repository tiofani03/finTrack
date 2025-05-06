package com.tiooooo.fintrack.adaptor

class SwiftAuthService : AuthService {
    override fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
//        AuthBridge.signIn(email, password, completion = callback)
    }
}