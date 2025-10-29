package com.tiooooo.fintrack.data.impl

import com.tiooooo.fintrack.data.api.RemoteConfigRepository
import com.tiooooo.fintrack.data.utils.RemoteConfigKey
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfig

class RemoteConfigRepositoryImpl(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val datastoreRepository: DatastoreRepository,
) : RemoteConfigRepository {

    override suspend fun getRemoteConfigValue() {
        try {
            println("remote config try fetching")
            firebaseRemoteConfig.fetchAndActivate()
            val stringRemoteConfig =
                firebaseRemoteConfig.getValue(RemoteConfigKey.STRING_REMOTE_CONFIG).asString()
            val featureFlagsMap =
                firebaseRemoteConfig.getValue(RemoteConfigKey.FEATURE_FLAGS).asString()
            println("remote config success fetching ${firebaseRemoteConfig.all}")
            datastoreRepository.setFeatureFlags(featureFlagsMap)
            datastoreRepository.setStringRemoteConfig(stringRemoteConfig)
        } catch (e: Exception) {
            println("remote config error at ${e.message}")
        }
    }
}