package com.tiooooo.fintrack.feature.settings.pages.settings

import cafe.adriel.voyager.core.model.screenModelScope
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.component.theme.AppTheme
import com.tiooooo.fintrack.data.impl.DatastoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingScreenModel(
    private val datastoreRepository: DatastoreRepository,
) : BaseScreenModel<SettingState, SettingIntent, SettingEffect>(SettingState()) {

    init {
        dispatch(SettingIntent.InitProfile)
    }

    override suspend fun handleIntent(intent: SettingIntent) {
        when (intent) {
            is SettingIntent.InitProfile -> initProfile()
            is SettingIntent.UpdateTheme -> updateTheme(intent.value)
            is SettingIntent.ExecuteLogout -> {
                sendEffect(SettingEffect.NavigateToLogin)
            }

            else -> setState { intent.reduce(state.value) }
        }
    }

    private fun initProfile() {
        screenModelScope.launch {
            val theme = datastoreRepository.themeApplication.first()
            val settingTitle =
                datastoreRepository.stringRemoteConfigValue.first()
            setState {
                it.copy(
                    settingTitle = settingTitle,
                    settingItemState = it.settingItemState.copy(
                        isDarkModeActive = theme == AppTheme.DARK.label
                    )
                )
            }
        }
    }

    private fun updateTheme(value: String) {
        screenModelScope.launch {
            setState {
                it.copy(
                    settingItemState = it.settingItemState.copy(
                        isDarkModeActive = value == AppTheme.DARK.label
                    )
                )
            }
            datastoreRepository.setThemeApplication(value)
        }
    }
}