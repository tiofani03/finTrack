package com.tiooooo.fintrack.component.utils

import java.text.NumberFormat
import java.util.Locale

actual fun formatRupiah(amount: Double): String {
    val locale = Locale("id", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(locale)
    return numberFormat.format(amount)
}