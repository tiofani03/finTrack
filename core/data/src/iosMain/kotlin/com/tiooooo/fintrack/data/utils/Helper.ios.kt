package com.tiooooo.fintrack.data.utils

import kotlinx.datetime.LocalDateTime
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.localeWithLocaleIdentifier
import platform.Foundation.numberWithDouble


actual fun formatRupiah(amount: Double): String {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = 2u
    numberFormatter.locale = NSLocale("id_ID")

    val number = NSNumber.numberWithDouble(amount)
    return numberFormatter.stringFromNumber(number) ?: "Rp0"
}

actual fun LocalDateTime.formatToReadableString(): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = "dd MMM HH.mm"
    dateFormatter.locale = NSLocale.localeWithLocaleIdentifier("en_US")

    val calendar = NSCalendar.currentCalendar
    val dateComponents = NSDateComponents().apply {
        year = this@formatToReadableString.year.toLong()
        month = this@formatToReadableString.monthNumber.toLong()
        day = this@formatToReadableString.dayOfMonth.toLong()
        hour = this@formatToReadableString.hour.toLong()
        minute = this@formatToReadableString.minute.toLong()
    }

    val date = calendar.dateFromComponents(dateComponents)!!
    return dateFormatter.stringFromDate(date)
}