package com.tiooooo.fintrack.data.impl

import androidx.compose.ui.graphics.Color
import com.tiooooo.fintrack.data.api.CommonRepository

class CommonRepositoryImpl : CommonRepository {
    override suspend fun getAllColors(): List<Color> {
        val colorOptions = listOf(
            Color.White,
            Color(0xFFB388EB),
            Color(0xFF81ECEC),
            Color(0xFF74B9FF),
            Color(0xFFA29BFE),
            Color(0xFF55EFC4),
            Color(0xFFFFD6A5),
            Color(0xFFFFA69E),
            Color(0xFFFFF1C1),
            Color(0xFFFDFFCD),
            Color(0xFFBFD7EA),
            Color(0xFFE0E0E0),
            Color(0xFFB0BEC5),
            Color(0xFF90CAF9),
            Color(0xFFFFCDD2),
            Color(0xFFCFD8DC),
        )

        return colorOptions
    }
}