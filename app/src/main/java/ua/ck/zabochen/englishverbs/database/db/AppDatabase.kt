package ua.ck.zabochen.englishverbs.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.ck.zabochen.englishverbs.database.dao.VerbDao
import ua.ck.zabochen.englishverbs.database.entity.VerbEntity

@Database(entities = [VerbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun verbDao(): VerbDao
}