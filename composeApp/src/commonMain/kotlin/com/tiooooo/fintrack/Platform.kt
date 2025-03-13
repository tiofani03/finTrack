package com.tiooooo.fintrack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform