package com.tiooooo.fintrack.component.component.bottomNavigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavModel(
    val label: String,
    val slug: String,
    val iconNotSelected: ImageVector,
    val iconSelected: ImageVector,
)
