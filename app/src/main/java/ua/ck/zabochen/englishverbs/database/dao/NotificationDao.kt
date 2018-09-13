package ua.ck.zabochen.englishverbs.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import ua.ck.zabochen.englishverbs.database.entity.Notification

@Dao
interface NotificationDao {

    // Get notification List
    @Query("SELECT * FROM notification")
    fun getNotificationList(): List<Notification>

    // Get notification
    @Query("SELECT * FROM notification WHERE id = :id")
    fun getNotification(id: Int): Notification

    // Update notification
    @Update
    fun update(notification: Notification)
}