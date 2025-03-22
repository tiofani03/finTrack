package com.tiooooo.fintrack.component.utils

import androidx.compose.runtime.Composable

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}


@Composable
fun UiState<*>.onLoading(action: @Composable () -> Unit) {
    if (this is UiState.Loading) {
        action()
    }
}

@Composable
fun <T> UiState<T>.onSuccess(action: @Composable (T) -> Unit) {
    if (this is UiState.Success) {
        action(this.data)
    }
}

@Composable
fun UiState<*>.onError(action: @Composable (String) -> Unit) {
    if (this is UiState.Error) {
        action(this.message)
    }
}
