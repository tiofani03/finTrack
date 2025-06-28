package com.tiooooo.fintrack.data.user.api.repo

import dev.gitlive.firebase.auth.FirebaseUser

interface UserRepository {
  fun getCurrentUserId(): String?
  suspend fun createUser(
    user: FirebaseUser?,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  )
}