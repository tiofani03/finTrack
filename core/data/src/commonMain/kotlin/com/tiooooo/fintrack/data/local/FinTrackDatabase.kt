package com.tiooooo.fintrack.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.tiooooo.fintrack.data.local.converter.DateTimeConverter
import com.tiooooo.fintrack.data.local.dao.WalletDao
import com.tiooooo.fintrack.data.local.entity.WalletEntity
import kotlinx.coroutines.Dispatchers

@Database(entities = [WalletEntity::class], version = 1)
@TypeConverters(DateTimeConverter::class)
@ConstructedBy(FinTrackDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}

// Room compiler generates the `actual` implementations
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object FinTrackDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}


fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setQueryCoroutineContext(Dispatchers.Default)
        .build()
}