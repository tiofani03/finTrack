package com.tiooooo.fintrack.feature.settings.pages.settings

import androidx.compose.foundation.lazy.LazyListState
import com.tiooooo.fintrack.component.component.sheet.RadioItem
import com.tiooooo.fintrack.feature.settings.pages.settings.helper.getCurrencyList
import com.tiooooo.fintrack.feature.settings.pages.settings.helper.getLanguageList

sealed interface SettingEffect {
    data object NavigateToLogin : SettingEffect
}

data class SettingState(
    val settingTitle: String = "",
    val settingItemState: SettingItemState = SettingItemState(),
    val settingBottomSheetState: SettingBottomSheetState = SettingBottomSheetState(),
    val lazyListState: LazyListState = LazyListState(),
)

data class SettingItemState(
    val isDarkModeActive: Boolean = false,
    val currency: RadioItem<String> = getCurrencyList().first(),
    val language: RadioItem<String> = getLanguageList().first(),
)

data class SettingBottomSheetState(
    val isShowLanguageSheetState: Boolean = false,
    val isShowCurrencySheetState: Boolean = false,
)

sealed interface SettingIntent {
    fun reduce(state: SettingState): SettingState = state

    data object ExecuteLogout : SettingIntent
    data object InitProfile : SettingIntent
    data class UpdateTheme(val value: String) : SettingIntent
    data class UpdateLanguage(val value: RadioItem<String>) : SettingIntent {
        override fun reduce(state: SettingState): SettingState {
            return state.copy(
                settingItemState = state.settingItemState.copy(
                    language = value
                ),
                settingBottomSheetState = state.settingBottomSheetState.copy(
                    isShowLanguageSheetState = false
                )
            )
        }
    }
    data class ShowLanguageSheet(val value: Boolean) : SettingIntent {
        override fun reduce(state: SettingState): SettingState {
            return state.copy(
                settingBottomSheetState = state.settingBottomSheetState.copy(
                    isShowLanguageSheetState = value
                )
            )
        }
    }
    data class UpdateCurrency(val value: RadioItem<String>) : SettingIntent {
        override fun reduce(state: SettingState): SettingState {
            return state.copy(
                settingItemState = state.settingItemState.copy(
                    currency = value
                ),
                settingBottomSheetState = state.settingBottomSheetState.copy(
                    isShowCurrencySheetState = false
                )
            )
        }
    }
    data class ShowCurrencySheet(val value: Boolean) : SettingIntent {
        override fun reduce(state: SettingState): SettingState {
            return state.copy(
                settingBottomSheetState = state.settingBottomSheetState.copy(
                    isShowCurrencySheetState = value
                )
            )
        }
    }
}

data class SettingActions(
    val onLogoutClick: () -> Unit = {},
    val onThemeChanged: (String) -> Unit = {},
    val onShowCurrencySheet: (Boolean) -> Unit = {},
    val onShowLanguageSheet: (Boolean) -> Unit = {},
)