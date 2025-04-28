package com.tiooooo.fintrack.pages.settings

sealed interface SettingEffect {
    data object NavigateToLogin : SettingEffect
}

data class SettingState(
    val activeTheme: String = "",
    val isShowDialogTheme: Boolean = false,
)

sealed interface SettingIntent {
    data object ExecuteLogout : SettingIntent
    data object InitProfile : SettingIntent
    data class UpdateTheme(val value: String) : SettingIntent
    data class ShowDialogTheme(val value: Boolean) : SettingIntent
}