package com.tiooooo.fintrack.pages.transaction.add

import com.tiooooo.fintrack.base.BaseScreenModelRedux
import com.tiooooo.fintrack.data.api.CategoryRepository
import com.tiooooo.fintrack.data.api.TransactionRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.local.entity.toWalletItem

class AddTransactionScreenModel(
    private val transactionRepository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val categoryRepository: CategoryRepository,
) : BaseScreenModelRedux<AddTransactionState, AddTransactionIntent, AddTransactionEffect>(
    AddTransactionState()
) {

    init {
        dispatch(AddTransactionIntent.Initial)
    }

    override fun reducer(state: AddTransactionState, intent: AddTransactionIntent): AddTransactionState {
        return when (intent) {
            is AddTransactionIntent.OnTransactionNameChanged -> state.copy(transactionName = intent.name)
            is AddTransactionIntent.OnTransactionAmountChanged -> state.copy(transactionAmount = intent.amount)
            is AddTransactionIntent.OnTransactionDateChanged -> state.copy(transactionDate = intent.date)
            is AddTransactionIntent.OnTransactionTypeChanged -> state.copy(transactionType = intent.type)
            is AddTransactionIntent.OnCategoryChanged -> state.copy(selectedCategoryId = intent.category.id)
            is AddTransactionIntent.OnWalletChanged -> state.copy(selectedWalletId = intent.walletId)
            else -> state
        }
    }

    override suspend fun handleIntentSideEffect(intent: AddTransactionIntent) {
        when (intent) {
            is AddTransactionIntent.Initial -> {
                walletRepository.getAllWallets().collect { wallets ->
                    setState { it.copy(wallets = wallets.map { walletEntity -> walletEntity.toWalletItem() }) }
                }
                categoryRepository.getAllCategories().collect { categories ->
                    setState { it.copy(categories = categories) }
                }
            }

            is AddTransactionIntent.OnTransactionAdded -> {
                val transaction = intent.transaction
                transactionRepository.insertTransaction(transaction)
                sendEffect(AddTransactionEffect.AddTransaction)
            }

            else -> Unit
        }
    }
}
