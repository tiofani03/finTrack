package com.tiooooo.fintrack.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun formatRupiah(amount: Double): String {
    val locale = Locale("id", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(locale)
    return numberFormat.format(amount)
}

@RequiresApi(Build.VERSION_CODES.O)
actual fun LocalDateTime.formatToReadableString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM HH.mm", Locale.ENGLISH)
    return this.toJavaLocalDateTime().format(formatter)
}