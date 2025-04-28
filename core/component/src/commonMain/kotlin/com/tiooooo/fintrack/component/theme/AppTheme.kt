package com.tiooooo.fintrack.component.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tiooooo.fintrack.data.impl.DatastoreRepository
import kotlinx.coroutines.flow.map

enum class AppTheme(val label: String) {
    SYSTEM("theme_system"),
    LIGHT("theme_light_mode"),
    DARK("theme_dark_mode");

    companion object {
        fun fromValue(value: String): AppTheme {
            return entries.find { it.label == value } ?: SYSTEM
        }
    }

    fun toDarkMode(): Boolean? = when (this) {
        SYSTEM -> null
        LIGHT -> false
        DARK -> true
    }
}


@Composable
fun rememberAppTheme(datastoreRepository: DatastoreRepository): Boolean {
    val appTheme by datastoreRepository.themeApplication
        .map { AppTheme.fromValue(it) }
        .collectAsState(initial = AppTheme.SYSTEM)

    return when (appTheme.toDarkMode()) {
        true -> true
        false -> false
        null -> isSystemInDarkTheme()
    }
}
