package com.tiooooo.fintrack.data.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

expect fun formatRupiah(amount: Double): String
expect fun LocalDateTime.formatToReadableString(formatedDate: String = "dd MMM HH.mm"): String

fun timeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}