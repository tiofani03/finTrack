package com.tiooooo.fintrack.feature.wallet.pages.list

import androidx.compose.ui.graphics.Color
import com.tiooooo.fintrack.component.resources.IconHelper
import com.tiooooo.fintrack.data.model.wallet.WalletItem
import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletEffect.NavigateToAddWallet
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletEffect.NavigateToDetailWallet
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.LoadData
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.OnAddWalletClicked
import com.tiooooo.fintrack.feature.wallet.pages.list.WalletIntent.OnWalletClicked
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

suspend fun handleWalletIntentSideEffect(
  intent: WalletIntent,
  walletRepository: WalletFirestoreRepository,
  setState: ((WalletState) -> WalletState) -> Unit,
  sendEffect: suspend (WalletEffect) -> Unit,
  currentState: () -> WalletState,
  screenModelScope: CoroutineScope,
  reducer: (WalletState, WalletIntent) -> WalletState,

  ) {
  when (intent) {
    is LoadData -> {
      screenModelScope.launch {
        println("Iin get wallets")
        walletRepository.getWallets(
          onSuccess = { data ->
            println("Iin get wallets $data")
            setState {
              currentState().copy(
                wallets = data.map {
                  WalletItem(
                    id = it.id,
                    name = it.name,
                    amount = it.balance.toString(),
                    amountDouble = it.balance,
                    color = Color(it.color),
                    image = IconHelper.icLoginApple,
                  )
                },
                isLoading = false
              )
            }
          },
          onError = {
            println("Iin get wallets error $it")
          }
        )
      }

      screenModelScope.launch {
//                walletRepository.getTotalAmount().collectLatest { total ->
//                    setState {
//                        currentState().copy(
//                            totalAmount = total
//                        )
//                    }
//                }
      }
    }

    is OnWalletClicked -> {
      sendEffect(NavigateToDetailWallet(intent.id))
    }

    is OnAddWalletClicked -> {
      sendEffect(NavigateToAddWallet)
    }

    else -> setState { reducer(it, intent) }
  }
}

