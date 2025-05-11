package com.tiooooo.fintrack.adaptor

import com.google.firebase.auth.FirebaseAuth

class AndroidAuthService : AuthService {
    override fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, null)
            } else {
                callback(false, it.exception?.message)
            }
        }
    }

    override fun signInWithGoogle(callback: (Boolean, String?) -> Unit) {

    }
}