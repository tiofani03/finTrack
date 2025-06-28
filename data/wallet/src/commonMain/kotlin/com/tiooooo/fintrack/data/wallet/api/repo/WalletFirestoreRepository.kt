package com.tiooooo.fintrack.data.wallet.api.repo

import com.tiooooo.fintrack.data.wallet.api.model.Wallet

interface WalletFirestoreRepository {

  suspend fun createWallet(
    wallet: Wallet,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
  )

  suspend fun getWallets(
    onSuccess: (List<Wallet>) -> Unit,
    onError: (String) -> Unit
  )

  suspend fun getWalletById(
    userId: String,
    walletId: String,
    onSuccess: (Wallet?) -> Unit,
    onError: (String) -> Unit
  )

  suspend fun updateWallet(
    userId: String,
    walletId: String,
    wallet: Wallet,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  )

  suspend fun deleteWallet(
    userId: String,
    walletId: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  )
}

