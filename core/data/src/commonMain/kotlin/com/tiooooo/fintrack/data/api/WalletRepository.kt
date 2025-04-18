package com.tiooooo.fintrack.data.api

import com.tiooooo.fintrack.data.local.entity.WalletEntity
import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    suspend fun insertWallet(wallet: WalletEntity)
    suspend fun updateWallet(wallet: WalletEntity)
    suspend fun deleteWallet(wallet: WalletEntity)
    suspend fun getWalletById(id: Long): WalletEntity?
    fun getAllWallets(): Flow<List<WalletEntity>>
    fun getTotalAmount(): Flow<Double>
}