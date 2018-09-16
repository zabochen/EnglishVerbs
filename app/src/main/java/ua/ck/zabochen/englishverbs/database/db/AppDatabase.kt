package ua.ck.zabochen.englishverbs.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.ck.zabochen.englishverbs.database.dao.SettingsDao
import ua.ck.zabochen.englishverbs.database.dao.VerbDao
import ua.ck.zabochen.englishverbs.database.entity.Settings
import ua.ck.zabochen.englishverbs.database.entity.Verb

@Database(entities = [Verb::class, Settings::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun verbDao(): VerbDao
    abstract fun settingsDao(): SettingsDao
}