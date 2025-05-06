package com.tiooooo.fintrack.data.impl

import com.tiooooo.fintrack.data.api.TransactionRepository
import com.tiooooo.fintrack.data.local.dao.TransactionDao
import com.tiooooo.fintrack.data.local.dao.TransactionWalletDao
import com.tiooooo.fintrack.data.local.dao.WalletDao
import com.tiooooo.fintrack.data.local.entity.toEntity
import com.tiooooo.fintrack.data.local.entity.toItem
import com.tiooooo.fintrack.data.model.transaction.TransactionItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val walletDao: WalletDao,
    private val transactionWalletDao: TransactionWalletDao,
) : TransactionRepository {

    override suspend fun insertTransaction(transaction: TransactionItem) {
        transactionDao.insertTransaction(transaction.toEntity())
        val selectedWallet = walletDao.getWalletById(transaction.walletId)
        selectedWallet?.let {
            val updatedWallet = it.copy(balance = it.balance + transaction.amount)
            walletDao.updateWallet(updatedWallet)
        }
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
        return transactionWalletDao.getTransactionsWithWallet().map { list ->
            list.map { transactionWithWallet ->
                val transaction = transactionWithWallet.transaction
                val walletName = transactionWithWallet.wallet?.name.orEmpty()

                transaction.toItem(walletName)
            }
        }

    }

    override fun getTransactionsByWallet(walletId: Long): Flow<List<TransactionItem>> {
        return transactionDao.getTransactionsByWallet(walletId)
            .map { list -> list.map { it.toItem() } }
    }

    override fun getTotalExpense(): Flow<Double> {
        return transactionDao.getTotalExpense()
    }

    override fun getTotalIncome(): Flow<Double> {
        return transactionDao.getTotalIncome()
    }
}