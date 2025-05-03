package com.tiooooo.fintrack.data.utils

import kotlinx.datetime.LocalDateTime
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle
import platform.Foundation.localeWithLocaleIdentifier
import platform.Foundation.numberWithDouble


actual fun formatRupiah(amount: Double): String {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = NSNumberFormatterCurrencyStyle
    numberFormatter.locale = NSLocale(localeIdentifier = "id_ID")

    val number = NSNumber.numberWithDouble(kotlin.math.abs(amount))
    val formatted = numberFormatter.stringFromNumber(number) ?: "Rp0"

    return if (amount < 0) "-$formatted" else formatted
}

actual fun LocalDateTime.formatToReadableString(formatedDate: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = formatedDate
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