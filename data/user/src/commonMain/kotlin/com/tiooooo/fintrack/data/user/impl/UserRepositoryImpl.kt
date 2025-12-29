package com.tiooooo.fintrack.data.user.impl

import com.tiooooo.fintrack.data.user.api.model.Category
import com.tiooooo.fintrack.data.user.api.model.Transaction
import com.tiooooo.fintrack.data.user.api.model.User
import com.tiooooo.fintrack.data.user.api.model.Wallet
import com.tiooooo.fintrack.data.user.api.repo.UserRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlin.time.Clock

class UserRepositoryImpl : UserRepository {
  override fun getCurrentUserId(): String? {
    return Firebase.auth.currentUser?.uid
  }

  override suspend fun createUser(
    user: FirebaseUser?,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) {
    try {
      if (user == null) {
        onError("User is not authenticated")
        return
      }

      val firestore = Firebase.firestore
      val userDoc = firestore.collection("users").document(user.uid)

      val snapshot = userDoc.get()
      if (snapshot.exists) {
        onError("already exists")
        return
      }

      val userData = User(
        id = user.uid,
        firstName = user.displayName?.split(" ")?.firstOrNull().orEmpty(),
        lastName = user.displayName?.split(" ")?.lastOrNull().orEmpty(),
        email = user.email.orEmpty(),
        photoUrl = user.photoURL.orEmpty(),
      )

      userDoc.set(userData)

      // Create default wallet
      val wallet = Wallet(
        name = "Dompet Utama",
        type = "cash",
        balance = 0.0,
      )

      val walletRef = userDoc.collection("wallets").add(wallet)
      val walletId = walletRef.id

      // Update user with default wallet ID
      userDoc.update(mapOf("defaultWalletId" to walletId))

      // Add default categories
      val defaultCategories = listOf(
        Category(name = "Makan", type = "expense", icon = "🍔", isDefault = true),
        Category(name = "Transport", type = "expense", icon = "🚌", isDefault = true),
        Category(name = "Gaji", type = "income", icon = "💰", isDefault = true),
      )

      val categoryCollection = userDoc.collection("categories")
      defaultCategories.forEach { category ->
        categoryCollection.add(category)
      }

      // Add dummy transactions
      val transaction1 = Transaction(
        amount = -15000.0,
        type = "expense",
        category = "makan",
        description = "Beli kopi",
      )

      val transaction2 = Transaction(
        amount = 2000000.0,
        type = "income",
        category = "gaji",
        description = "Gaji bulan ini",
      )

      walletRef.collection("transactions").add(transaction1)
      walletRef.collection("transactions").add(transaction2)

      // ==== STATS SETUP ====
      val statsCollection = userDoc.collection("stats")
      val now = Clock.System.now().toString()

      statsCollection.document("balance").set(
        mapOf(
          "totalBalance" to 0.0,
          "lastUpdated" to now
        )
      )

      statsCollection.document("summary_today").set(
        mapOf(
          "totalIncome" to 0.0,
          "totalExpense" to 0.0,
          "net" to 0.0,
          "date" to now
        )
      )

      statsCollection.document("summary_monthly").set(
        mapOf(
          "totalIncome" to 0.0,
          "totalExpense" to 0.0,
          "net" to 0.0,
          "month" to now
        )
      )

      statsCollection.document("category_totals").set(emptyMap<String, Any>())

      statsCollection.document("transactions_count").set(
        mapOf(
          "total" to 0,
          "income" to 0,
          "expense" to 0
        )
      )

      statsCollection.document("top_spending_category").set(
        mapOf(
          "name" to "",
          "amount" to 0.0
        )
      )

      onSuccess()
    } catch (e: Exception) {
      onError(e.message ?: "Gagal membuat user")
    }
  }
}