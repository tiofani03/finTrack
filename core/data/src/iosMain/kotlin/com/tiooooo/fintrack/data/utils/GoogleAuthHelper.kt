package com.tiooooo.fintrack.data.utils

//import cocoapods.FirebaseAuth.FIRAuth
//import cocoapods.GoogleSignIn.GIDSignIn
//import com.tiooooo.fintrack.data.model.auth.AccountInfo
//import dev.gitlive.firebase.auth.GoogleAuthProvider
//import kotlinx.cinterop.ExperimentalForeignApi
//import platform.UIKit.UIApplication
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//
//actual class GoogleAuthHelper {
//    @OptIn(ExperimentalForeignApi::class)
//    actual suspend fun signIn(): AccountInfo? = suspendCoroutine { continuation ->
//        try {
//            val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
//            if (rootViewController == null) {
//                continuation.resume(null)
//            } else {
//                GIDSignIn.sharedInstance.signInWithPresentingViewController(rootViewController) { gidSignInResult, nsError ->
//                    nsError?.let { println("Error While signing: $nsError") }
//                    val idToken = gidSignInResult?.user?.idToken?.tokenString
//                    val accessToken = gidSignInResult?.user?.accessToken?.tokenString.orEmpty()
//                    val profile = gidSignInResult?.user?.profile
//
//                    if (!idToken.isNullOrEmpty()) {
//                        FIRAuth.auth().signInWithCredential(
//                            credential = GoogleAuthProvider.credential(idToken, accessToken).ios,
//                            completion = { result, error ->
//                                if (error != null) {
//                                    println("Firebase sign in failed: $error")
//                                    continuation.resume(null)
//                                } else {
//                                    val user = result?.user
//                                    val googleUser = AccountInfo(
//                                        token = idToken,
//                                        displayName = profile?.name ?: user?.displayName ?: "",
//                                        profileImageUrl = profile?.imageURLWithDimension(320u)?.absoluteString,
//                                    )
//                                    continuation.resume(googleUser)
//                                }
//                            }
//                        )
//                    } else {
//                        continuation.resume(null)
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            continuation.resume(null)
//        }
//    }
//
//    @OptIn(ExperimentalForeignApi::class)
//    actual fun signOut() {
//        GIDSignIn.sharedInstance.signOut()
//        FIRAuth.auth().signOut(null)
//    }
//
//    @OptIn(ExperimentalForeignApi::class)
//    actual fun getAccountInfo(): AccountInfo? {
//        val currentUser = FIRAuth.auth().currentUser
//        return currentUser?.let { user ->
//            AccountInfo(
//                token = user.tenantID.orEmpty(),
//                displayName = user.displayName ?: GIDSignIn.sharedInstance.currentUser?.profile?.name.orEmpty(),
//                profileImageUrl = user.photoURL?.absoluteString ?: GIDSignIn.sharedInstance.currentUser?.profile?.imageURLWithDimension(320u)?.absoluteString
//            )
//        }
//    }
//}