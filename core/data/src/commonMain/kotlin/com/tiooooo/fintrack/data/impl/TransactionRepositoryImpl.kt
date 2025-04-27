package com.tiooooo.fintrack.data.impl

import com.tiooooo.fintrack.data.api.TransactionRepository
import com.tiooooo.fintrack.data.local.dao.TransactionDao
import com.tiooooo.fintrack.data.local.entity.toEntity
import com.tiooooo.fintrack.data.local.entity.toItem
import com.tiooooo.fintrack.data.model.transaction.TransactionItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
) : TransactionRepository {

    override suspend fun insertTransaction(transaction: TransactionItem) {
        transactionDao.insertTransaction(transaction.toEntity())
    }

    override suspend fun updateTransaction(transaction: TransactionItem) {
        transactionDao.updateTransaction(transaction.toEntity())
    }

    override suspend fun deleteTransaction(transaction: TransactionItem) {
        transactionDao.deleteTransaction(transaction.toEntity())
    }

    override suspend fun getTransactionById(id: Long): TransactionItem? {
        return transactionDao.getTransactionById(id)?.toItem()
    }

    override fun getAllTransactions(): Flow<List<TransactionItem>> {
        return transactionDao.getAllTransactions().map { list -> list.map { it.toItem() } }
    }

    override fun getTransactionsByWallet(walletId: Long): Flow<List<TransactionItem>> {
        return transactionDao.getTransactionsByWallet(walletId).map { list -> list.map { it.toItem() } }
    }

    override fun getTotalExpense(): Flow<Double> {
        return transactionDao.getTotalExpense()
    }

    override fun getTotalIncome(): Flow<Double> {
        return transactionDao.getTotalIncome()
    }
}