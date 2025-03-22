package com.tiooooo.fintrack.pages.settings

import androidx.compose.foundation.lazy.LazyListState
import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.utils.ScrollStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingScreenModel : ScreenModel, ScrollStateManager {
    private val _settingList = MutableStateFlow<List<String>>(emptyList())
    val settingList: StateFlow<List<String>> = _settingList

    init {
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _settingList.value = List(500) { "Ini adalah data ke ${it + 1}" }
        }
    }

    private val _lazyListState = MutableStateFlow(LazyListState())
    override val lazyListState: StateFlow<LazyListState> = _lazyListState

    override fun updateState(state: LazyListState) {
        _lazyListState.value = state
    }

    fun refreshData(onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            val newList = List(100) { index ->
                "Data refreshed ke ${index + 1}"
            }
            _settingList.value = newList.toMutableList()
            onComplete()
        }
    }
}