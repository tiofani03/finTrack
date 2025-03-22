package com.tiooooo.fintrack.component.component.bottomNavigation

import org.jetbrains.compose.resources.DrawableResource

data class BottomNavModel(
    val label: String,
    val slug: String,
    val iconNotSelected: DrawableResource,
    val iconSelected: DrawableResource,
)
