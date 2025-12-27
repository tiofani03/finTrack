package com.tiooooo.fintrack.data.api

interface RemoteConfigRepository {
  suspend fun getRemoteConfigValue()
}