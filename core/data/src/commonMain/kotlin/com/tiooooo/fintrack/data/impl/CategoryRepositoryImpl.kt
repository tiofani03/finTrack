package com.tiooooo.fintrack.data.impl

import com.tiooooo.fintrack.data.api.CategoryRepository
import com.tiooooo.fintrack.data.local.dao.CategoryDao
import com.tiooooo.fintrack.data.local.entity.toEntity
import com.tiooooo.fintrack.data.local.entity.toItem
import com.tiooooo.fintrack.data.model.category.CategoryItem
import com.tiooooo.fintrack.data.model.category.CategoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override suspend fun insertCategory(category: CategoryItem) {
        categoryDao.insertCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: CategoryItem) {
        categoryDao.updateCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: CategoryItem) {
        categoryDao.deleteCategory(category.toEntity())
    }

    override suspend fun getCategoryById(id: Long): CategoryItem? {
        return categoryDao.getCategoryById(id)?.toItem()
    }

    override fun getAllCategories(): Flow<List<CategoryItem>> {
        return categoryDao.getAllCategories().map { it.map { entity -> entity.toItem() } }
    }

    override fun getCategoriesByType(type: CategoryType): Flow<List<CategoryItem>> {
        return categoryDao.getCategoriesByType(type.name).map { it.map { entity -> entity.toItem() } }
    }
}
