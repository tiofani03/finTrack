package com.tiooooo.fintrack

interface Platform {
    val serverId: String
}

expect fun getPlatform(): Platform