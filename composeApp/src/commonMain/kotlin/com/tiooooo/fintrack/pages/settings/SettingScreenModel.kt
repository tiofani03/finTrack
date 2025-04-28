package com.tiooooo.fintrack.pages.settings

import androidx.compose.foundation.lazy.LazyListState
import com.tiooooo.fintrack.component.base.BaseScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import com.tiooooo.fintrack.data.impl.DatastoreRepository
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

    override fun reducer(state: SettingState, intent: SettingIntent): SettingState {
        return when (intent) {
            is SettingIntent.ShowDialogTheme -> state.copy(isShowDialogTheme = intent.value)
            is SettingIntent.UpdateTheme -> state.copy(activeTheme = intent.value)
            else -> state
        }
    }

    override suspend fun handleIntentSideEffect(intent: SettingIntent) {
        when (intent) {
            is SettingIntent.InitProfile -> {
                val theme = datastoreRepository.themeApplication.first()
                setState { it.copy(activeTheme = theme) }
            }

            is SettingIntent.UpdateTheme -> {
                datastoreRepository.setThemeApplication(intent.value)
            }

            else -> Unit
        }
    }
}