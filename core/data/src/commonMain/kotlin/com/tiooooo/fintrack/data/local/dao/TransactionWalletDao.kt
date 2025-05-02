package com.tiooooo.fintrack.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tiooooo.fintrack.data.local.entity.TransactionWithWallet
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionWalletDao {

    @Transaction
    @Query("SELECT * FROM `transaction` ORDER BY createdAt DESC")
    fun getTransactionsWithWallet(): Flow<List<TransactionWithWallet>>
}