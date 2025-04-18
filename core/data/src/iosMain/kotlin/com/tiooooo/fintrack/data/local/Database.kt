package com.tiooooo.fintrack.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUserDomainMask

//@OptIn(ExperimentalForeignApi::class)
//fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
//    val documentsDirectory = NSFileManager.defaultManager.URLForDirectory(
//        directory = NSDocumentDirectory,
//        inDomain = NSUserDomainMask,
//        appropriateForURL = null,
//        create = true,
//        error = null,
//    )?.path() ?: NSHomeDirectory()
//
//    val dbPath = "$documentsDirectory/app.db"
//    return Room.databaseBuilder<AppDatabase>(
//        name = dbPath,
//        factory = { AppDatabase::class.instantiateImpl()}
//    )
//}

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = documentDirectory() + "/fintrack.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )

    return requireNotNull(documentDirectory?.path)
}