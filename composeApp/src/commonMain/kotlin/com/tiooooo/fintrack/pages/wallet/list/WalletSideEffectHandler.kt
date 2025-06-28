package com.tiooooo.fintrack.pages.wallet.list

import androidx.compose.ui.graphics.Color
import com.tiooooo.fintrack.data.model.wallet.WalletItem
import com.tiooooo.fintrack.data.wallet.api.repo.WalletFirestoreRepository
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.ic_login_facebook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

suspend fun handleWalletIntentSideEffect(
  intent: WalletIntent,
  walletRepository: WalletFirestoreRepository,
  setState: ((WalletState) -> WalletState) -> Unit,
  sendEffect: suspend (WalletEffect) -> Unit,
  currentState: () -> WalletState,
  screenModelScope: CoroutineScope,

  ) {
  when (intent) {
    is WalletIntent.LoadData -> {
      screenModelScope.launch {
        walletRepository.getWallets(
          onSuccess = { data ->
            setState {
              currentState().copy(
                wallets = data.map {
                  WalletItem(
                    id = it.id,
                    name = it.name,
                    amount = it.balance.toString(),
                    amountDouble = it.balance,
                    color = Color(it.color),
                    image = Res.drawable.ic_login_facebook,
                  )
                },
                isLoading = false
              )
            }
          },
          onError = {

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

    is WalletIntent.OnWalletClicked -> {
      sendEffect(WalletEffect.NavigateToDetailWallet(intent.id))
    }

    is WalletIntent.OnAddWalletClicked -> {
      sendEffect(WalletEffect.NavigateToAddWallet)
    }

    else -> Unit
  }
}

