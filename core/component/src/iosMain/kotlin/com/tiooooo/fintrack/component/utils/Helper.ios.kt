package com.tiooooo.fintrack.component.utils

import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumber
import platform.Foundation.NSLocale
import platform.Foundation.numberWithDouble


actual fun formatRupiah(amount: Double): String {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = 2u
    numberFormatter.locale = NSLocale("id_ID")

    val number = NSNumber.numberWithDouble(amount)
    return numberFormatter.stringFromNumber(number) ?: "Rp0"
}