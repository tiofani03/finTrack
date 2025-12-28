package com.tiooooo.fintrack.pages.transaction.add

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.data.api.CategoryRepository
import com.tiooooo.fintrack.data.api.TransactionRepository
import com.tiooooo.fintrack.data.api.WalletRepository
import com.tiooooo.fintrack.data.local.entity.toWalletItem
import kotlinx.coroutines.launch

class AddTransactionScreenModel(
    private val transactionRepository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val categoryRepository: CategoryRepository,
) : BaseScreenModel<AddTransactionState, AddTransactionIntent, AddTransactionEffect>(
    AddTransactionState()
) {

    init {
        dispatch(AddTransactionIntent.Initial)
    }

    fun reducer(state: AddTransactionState, intent: AddTransactionIntent): AddTransactionState {
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

    override suspend fun handleIntent(intent: AddTransactionIntent) {
        when (intent) {
            is AddTransactionIntent.Initial -> {
                screenModelScope.launch {
                    launch {
                        walletRepository.getAllWallets().collect { wallets ->
                            setState { it.copy(wallets = wallets.map { walletEntity -> walletEntity.toWalletItem() }) }
                        }
                    }
                    launch {
                        categoryRepository.getAllCategories().collect { categories ->
                            setState { it.copy(categories = categories) }
                        }
                    }
                }
            }

            is AddTransactionIntent.OnTransactionAdded -> {
                val transaction = intent.transaction
                transactionRepository.insertTransaction(transaction)
                sendEffect(AddTransactionEffect.AddTransaction)
            }

            else -> setState { reducer(it, intent) }
        }
    }
}
