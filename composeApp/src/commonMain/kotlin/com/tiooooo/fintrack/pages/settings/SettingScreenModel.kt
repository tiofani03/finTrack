package com.tiooooo.fintrack.pages.settings

import androidx.compose.foundation.lazy.LazyListState
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import com.tiooooo.fintrack.data.impl.DatastoreRepository
import com.tiooooo.fintrack.data.utils.RemoteConfigKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class SettingScreenModel(
    private val datastoreRepository: DatastoreRepository,
) : BaseScreenModel<SettingState, SettingIntent, SettingEffect>(SettingState()),
    ScrollStateManager {

    init {
        dispatch(SettingIntent.InitProfile)
    }

    private val _lazyListState = MutableStateFlow(LazyListState())
    override val lazyListState: StateFlow<LazyListState> = _lazyListState

    override fun updateState(state: LazyListState) {
        _lazyListState.value = state
    }

    fun reducer(state: SettingState, intent: SettingIntent): SettingState {
        return when (intent) {
            is SettingIntent.ShowDialogTheme -> state.copy(isShowDialogTheme = intent.value)
            is SettingIntent.UpdateTheme -> state.copy(activeTheme = intent.value)
            else -> state
        }
    }

    override suspend fun handleIntent(intent: SettingIntent) {
        when (intent) {
            is SettingIntent.InitProfile -> {
                val theme = datastoreRepository.themeApplication.first()
                val isDarkMode =
                    datastoreRepository.isFeatureEnabled(RemoteConfigKey.IS_ENABLE_DARK_MODE)
                val settingTitle =
                    datastoreRepository.stringRemoteConfigValue.first()
                setState {
                    it.copy(
                        activeTheme = theme,
                        isEnableDarkMode = isDarkMode,
                        settingTitle = settingTitle
                    )
                }
            }

            is SettingIntent.UpdateTheme -> {
                datastoreRepository.setThemeApplication(intent.value)
            }

            is SettingIntent.ExecuteLogout -> {
                sendEffect(SettingEffect.NavigateToLogin)
            }

            else -> setState { reducer(it, intent) }
        }
    }
}