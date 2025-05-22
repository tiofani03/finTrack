package com.tiooooo.fintrack.data.utils

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tiooooo.fintrack.data.model.auth.AccountInfo
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class GoogleAuthHelper(
    private val activity: ComponentActivity
) {
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var complete: ((AccountInfo?) -> Unit)? = null

    init {
        launcher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnSuccessListener {
                        complete?.invoke(
                            AccountInfo(
                                token = account.idToken ?: "",
                                displayName = account.displayName ?: "",
                                profileImageUrl = account.photoUrl.toString()
                            )
                        )
                    }
                    .addOnFailureListener {
                        complete?.invoke(null)
                    }
            } catch (e: Exception) {
                complete?.invoke(null)
            }
        }
    }

    private fun launchSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(com.tiooooo.fintrack.core.data.BuildConfig.CLIENT_ID)
            .requestProfile()
            .requestId()
            .build()
        val client = GoogleSignIn.getClient(activity, gso)
        client.signOut().addOnCompleteListener { launcher?.launch(client.signInIntent) }
    }

    actual suspend fun signIn(): AccountInfo? = suspendCancellableCoroutine { cont ->
        complete = { result ->
            complete = null
            cont.resume(result)
        }
        launchSignIn()
    }

    actual fun signOut() {
        FirebaseAuth.getInstance().signOut()
        GoogleSignIn.getClient(activity, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
        launcher?.unregister()
        launcher = null
    }

    actual fun getAccountInfo(): AccountInfo? {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            return AccountInfo(
                token = user.uid,
                displayName = user.displayName.orEmpty(),
                profileImageUrl = user.photoUrl.toString()
            )
        }
        return null
    }
}
