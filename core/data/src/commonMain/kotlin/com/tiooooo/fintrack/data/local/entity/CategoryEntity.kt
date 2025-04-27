package com.tiooooo.fintrack.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tiooooo.fintrack.data.model.category.CategoryItem
import com.tiooooo.fintrack.data.model.category.CategoryType
import com.tiooooo.fintrack.data.utils.timeNow
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val icon: String,
    val type: CategoryType,
    val createdAt: LocalDateTime = timeNow(),
    val updatedAt: LocalDateTime = timeNow()
)

fun CategoryEntity.toItem(): CategoryItem {
    return CategoryItem(
        id = id,
        name = name,
        icon = icon,
        type = type,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun CategoryItem.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        icon = icon,
        type = type,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}