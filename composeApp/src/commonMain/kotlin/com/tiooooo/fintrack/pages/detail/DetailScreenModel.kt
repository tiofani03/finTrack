package com.tiooooo.fintrack.pages.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.tiooooo.fintrack.component.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenModel : ScreenModel {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val uiState: StateFlow<UiState<String>> = _uiState

    fun loadItemData(id: String) {
        _uiState.value = UiState.Loading
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            _uiState.value = UiState.Success(id)
        }
    }
}