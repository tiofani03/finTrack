package com.tiooooo.fintrack.data.api

import com.tiooooo.fintrack.data.model.transaction.TransactionItem
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insertTransaction(transaction: TransactionItem)
    suspend fun updateTransaction(transaction: TransactionItem)
    suspend fun deleteTransaction(transaction: TransactionItem)
    suspend fun getTransactionById(id: Long): TransactionItem?
    fun getAllTransactions(): Flow<List<TransactionItem>>
    fun getTransactionsByWallet(walletId: Long): Flow<List<TransactionItem>>
    fun getTotalExpense(): Flow<Double>
    fun getTotalIncome(): Flow<Double>
}