package com.tiooooo.fintrack

interface Platform {
    val name: String
    val isAndroid: Boolean
}

expect fun getPlatform(): Platform