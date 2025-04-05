package com.tiooooo.fintrack.data.wallet.api

import com.tiooooo.fintrack.pages.wallet.components.WalletItem

interface WalletRepository {
    suspend fun getWalletItems(): List<WalletItem>
}
