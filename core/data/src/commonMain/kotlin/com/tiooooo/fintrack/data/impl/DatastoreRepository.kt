package com.tiooooo.fintrack.data.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.tiooooo.fintrack.data.utils.RemoteConfigKey.FEATURE_FLAGS
import com.tiooooo.fintrack.data.utils.RemoteConfigKey.STRING_REMOTE_CONFIG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class DatastoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = stringPreferencesKey("theme_key")
    private val stringRemoteConfig = stringPreferencesKey(STRING_REMOTE_CONFIG)
    private val featureFlagsKey = stringPreferencesKey(FEATURE_FLAGS)

    val themeApplication: Flow<String> = dataStore.data
        .map { it[themeKey] ?: "theme_dark_mode" }

    val stringRemoteConfigValue: Flow<String> = dataStore.data
        .map { it[stringRemoteConfig] ?: "" }

    private val featureFlags: Flow<String> = dataStore.data
        .map { it[featureFlagsKey] ?: "" }

    private val featureFlagsMap: Flow<Map<String, Boolean>> = featureFlags
        .map { jsonString ->
            try {
                Json.decodeFromString<Map<String, Boolean>>(jsonString)
            } catch (e: Exception) {
                emptyMap()
            }
        }

    suspend fun isFeatureEnabled(flagName: String): Boolean {
        return featureFlagsMap.first()[flagName] == true
    }

    suspend fun setThemeApplication(key: String) {
        dataStore.edit { it[themeKey] = key }
    }

    suspend fun setStringRemoteConfig(key: String) {
        dataStore.edit { it[stringRemoteConfig] = key }
    }

    suspend fun setFeatureFlags(json: String) {
        dataStore.edit { it[featureFlagsKey] = json }
    }
}
