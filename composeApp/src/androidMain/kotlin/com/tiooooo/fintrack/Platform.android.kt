package com.tiooooo.fintrack

import android.os.Build

class AndroidPlatform : Platform {
    override val serverId: String = BuildConfig.CLIENT_ID
}

actual fun getPlatform(): Platform = AndroidPlatform()