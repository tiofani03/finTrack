package com.tiooooo.fintrack.data.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

expect fun formatRupiah(amount: Double): String
expect fun LocalDateTime.formatToReadableString(): String

fun timeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}