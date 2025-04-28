package com.tiooooo.fintrack.data.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = stringPreferencesKey("theme_key")

    val themeApplication: Flow<String> = dataStore.data.map { preferences ->
        preferences[themeKey] ?: "theme_dark_mode"
    }

    suspend fun setThemeApplication(key: String) {
        dataStore.edit { preferences ->
            preferences[themeKey] = key
        }
    }
}
