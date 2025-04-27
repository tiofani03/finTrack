package com.tiooooo.fintrack.data.model.category

import kotlinx.datetime.LocalDateTime

data class CategoryItem(
    val id: Long,
    val name: String,
    val icon: String,
    val type: CategoryType,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)