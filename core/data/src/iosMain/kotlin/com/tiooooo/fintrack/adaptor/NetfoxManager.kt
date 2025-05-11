package com.tiooooo.fintrack.adaptor

import cocoapods.netfox.NFX
import kotlinx.cinterop.ExperimentalForeignApi

object NetfoxManager {
    @OptIn(ExperimentalForeignApi::class)
    fun startNetfox() {
        NFX.sharedInstance().start()
    }
}