package com.tiooooo.fintrack.data.wallet.impl

import com.tiooooo.fintrack.data.wallet.api.model.Wallet
import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlin.time.Clock

class WalletFirestoreRepositoryImpl : WalletFirestoreRepository {

  private val firestore
    get() = Firebase.firestore

  override suspend fun createWallet(
    wallet: Wallet,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
  ) {
    try {
      val userId = Firebase.auth.currentUser?.uid
        ?: throw IllegalStateException("User is not authenticated")
      val walletId = "wallet-${Clock.System.now().toEpochMilliseconds()}"
      val walletRef = firestore
        .collection("users")
        .document(userId)
        .collection("wallets")
        .document(walletId)

      val walletWithId = wallet.copy(id = walletId)
      walletRef.set(walletWithId)

      onSuccess(walletId)
    } catch (e: Exception) {
      onError(e.message ?: "Gagal membuat dompet")
    }
  }


  override suspend fun getWallets(
//    userId: String,
    onSuccess: (List<Wallet>) -> Unit,
    onError: (String) -> Unit
  ) {
    try {
      val userId = Firebase.auth.currentUser?.uid
        ?: throw IllegalStateException("User is not authenticated")
      val walletSnapshot = firestore

        .collection("users")
        .document(userId)
        .collection("wallets")
        .get()

      val wallets = walletSnapshot.documents.mapNotNull { doc ->
        if (doc.exists) doc.data<Wallet>().copy(id = doc.id) else null
      }
      onSuccess(wallets)
    } catch (e: Exception) {
      println("Iin get wallets error: ${e.message}")
      onError(e.message ?: "Gagal mengambil daftar dompet")
    }
  }

  override suspend fun getWalletById(
    userId: String,
    walletId: String,
    onSuccess: (Wallet?) -> Unit,
    onError: (String) -> Unit
  ) {
    try {
      val doc = firestore
        .collection("users")
        .document(userId)
        .collection("wallets")
        .document(walletId)
        .get()

      val wallet = if (doc.exists) doc.data<Wallet>().copy(id = doc.id) else null
      onSuccess(wallet)
    } catch (e: Exception) {
      onError(e.message ?: "Gagal mengambil dompet")
    }
  }

  override suspend fun updateWallet(
    userId: String,
    walletId: String,
    wallet: Wallet,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) {
    try {
      val updatedWallet = wallet.copy(updatedAt = Clock.System.now())
      firestore.collection("users")
        .document(userId)
        .collection("wallets")
        .document(walletId)
        .set(updatedWallet)
      onSuccess()
    } catch (e: Exception) {
      onError(e.message ?: "Gagal mengupdate dompet")
    }
  }

  override suspend fun deleteWallet(
    userId: String,
    walletId: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) {
    try {
      firestore.collection("users")
        .document(userId)
        .collection("wallets")
        .document(walletId)
        .delete()
      onSuccess()
    } catch (e: Exception) {
      onError(e.message ?: "Gagal menghapus dompet")
    }
  }
}

