package com.tiooooo.fintrack.data.local.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

object DateTimeConverter {
    private val zone = TimeZone.UTC

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }
}