package com.tiooooo.fintrack

import platform.Foundation.NSBundle

class IOSPlatform : Platform {
  override val serverId: String =
    NSBundle.mainBundle.infoDictionary?.get("GIDServerClientId") as? String ?: ""
}

actual fun getPlatform(): Platform = IOSPlatform()