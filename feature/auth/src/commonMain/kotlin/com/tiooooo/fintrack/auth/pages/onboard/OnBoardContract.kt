package com.tiooooo.fintrack.auth.pages.onboard

import com.tiooooo.fintrack.component.resources.IconHelper
import dev.gitlive.firebase.auth.FirebaseUser
import org.jetbrains.compose.resources.DrawableResource

sealed interface OnBoardEffect {
  data object NavigateToDashboard : OnBoardEffect
  data class ShowToast(val message: String) : OnBoardEffect
}

sealed interface OnBoardIntent {
  data class ShowToast(val message: String) : OnBoardIntent
  data class RegisterSuccess(val user: FirebaseUser?): OnBoardIntent
  data object NavigateToDashboard : OnBoardIntent
  data class SetLoading(val isLoading: Boolean) : OnBoardIntent
}

data class OnboardState(
  val screenItems : List<ScreenOnboardItem> = listOf(
    ScreenOnboardItem(
      imageRes = IconHelper.icOnBoardSort,
      title = "Track Income & Expenses Easily",
      description = "Gain full control over your money. Log every coffee, paycheck, and bill in seconds—no accounting degree required.",
    ),
    ScreenOnboardItem(
      imageRes = IconHelper.icOnboardData,
      title = "Manage Assets & Wallets",
      description = "Effortlessly track your cash flow. Connect your bank accounts or add manual wallets to see your total net worth at a glance.",
    ),
    ScreenOnboardItem(
      imageRes = IconHelper.icOnboardHappy,
      title = "Control Spending with Budgeting",
      description = "You don't need to be an accountant. Set simple limits on groceries or entertainment and we'll alert you before you go over.",
    ),
  ),
  val isLoading: Boolean = false,
)

data class ScreenOnboardItem(
  val imageRes: DrawableResource,
  val title: String,
  val description: String,
)