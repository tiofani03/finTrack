package com.tiooooo.fintrack.data.api

import androidx.compose.ui.graphics.Color

interface CommonRepository {
    suspend fun getAllColors(): List<Color>
}