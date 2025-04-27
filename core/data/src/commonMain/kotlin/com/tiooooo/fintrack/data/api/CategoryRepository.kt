package com.tiooooo.fintrack.data.api

import com.tiooooo.fintrack.data.local.entity.CategoryEntity
import com.tiooooo.fintrack.data.model.category.CategoryItem
import com.tiooooo.fintrack.data.model.category.CategoryType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insertCategory(category: CategoryItem)
    suspend fun updateCategory(category: CategoryItem)
    suspend fun deleteCategory(category: CategoryItem)
    suspend fun getCategoryById(id: Long): CategoryItem?
    fun getAllCategories(): Flow<List<CategoryItem>>
    fun getCategoriesByType(type: CategoryType): Flow<List<CategoryItem>> // income / expense
}
