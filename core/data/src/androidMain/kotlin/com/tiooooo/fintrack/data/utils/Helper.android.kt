package com.tiooooo.fintrack.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun formatRupiah(amount: Double): String {
    val symbols = DecimalFormatSymbols(Locale("in", "ID")).apply {
        currencySymbol = "Rp "
        groupingSeparator = '.'
        decimalSeparator = ','
    }

    val decimalFormat = DecimalFormat("#,##0", symbols)
    if (amount < 0) {
        return "-Rp${decimalFormat.format(-amount)}"
    }
    return "Rp${decimalFormat.format(amount)}"
}

@RequiresApi(Build.VERSION_CODES.O)
actual fun LocalDateTime.formatToReadableString(formatedDate: String): String {
    val formatter = DateTimeFormatter.ofPattern(formatedDate, Locale.ENGLISH)
    return this.toJavaLocalDateTime().format(formatter)
}