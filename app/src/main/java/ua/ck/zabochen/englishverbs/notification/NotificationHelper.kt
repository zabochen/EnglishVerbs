package ua.ck.zabochen.englishverbs.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationHelper(val activityContext: Context) {


    fun createNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationManager: NotificationManager = activityContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Channel ID
            val channelId: String = "verb_notification_channel"

            // Channel Name
            val channelName: CharSequence = "Verb Channel Name"

            // Channel Description
            val channelDescription: String = "Verb Channel Description"

            // Channel Priority
            val channelPriority: Int = NotificationManager.IMPORTANCE_HIGH

            // Notification Channel
            val notificationChannel: NotificationChannel = NotificationChannel(channelId, channelName, channelPriority)
            notificationChannel.description = channelDescription

            // Create
            notificationManager.createNotificationChannel(notificationChannel)
        }


    }

}