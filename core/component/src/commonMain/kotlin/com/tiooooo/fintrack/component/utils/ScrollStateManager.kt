package com.tiooooo.fintrack.component.utils

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.flow.StateFlow

interface ScrollStateManager {
    val lazyListState: StateFlow<LazyListState>
    fun updateState(state: LazyListState)
}
