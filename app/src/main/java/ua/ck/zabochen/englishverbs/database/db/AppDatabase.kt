package ua.ck.zabochen.englishverbs.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.ck.zabochen.englishverbs.database.dao.NotificationDao
import ua.ck.zabochen.englishverbs.database.dao.VerbDao
import ua.ck.zabochen.englishverbs.database.entity.Notification
import ua.ck.zabochen.englishverbs.database.entity.Verb

@Database(entities = [Verb::class, Notification::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun verbDao(): VerbDao
    abstract fun notificationDao(): NotificationDao
}