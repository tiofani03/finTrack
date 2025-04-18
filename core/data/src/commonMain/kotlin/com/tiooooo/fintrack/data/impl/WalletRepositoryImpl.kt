package com.tiooooo.fintrack.data.impl

import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.local.dao.WalletDao
import com.tiooooo.fintrack.data.local.entity.WalletEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WalletRepositoryImpl(
    private val walletDao: WalletDao,
) : WalletRepository {
    override suspend fun insertWallet(wallet: WalletEntity) {
        walletDao.insertWallet(wallet)
    }

    override suspend fun updateWallet(wallet: WalletEntity) {
        walletDao.updateWallet(wallet)
    }

    override suspend fun deleteWallet(wallet: WalletEntity) {
        walletDao.deleteWallet(wallet)
    }

    override suspend fun getWalletById(id: Long): WalletEntity? {
        return walletDao.getWalletById(id)
    }

    override fun getAllWallets(): Flow<List<WalletEntity>> {
        return walletDao.getAllWallets()
    }

    override fun getTotalAmount(): Flow<Double>{
        return walletDao.getTotalWalletAmount()
    }
}