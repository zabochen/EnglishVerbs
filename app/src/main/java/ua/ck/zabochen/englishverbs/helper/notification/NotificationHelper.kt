package ua.ck.zabochen.englishverbs.helper.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import ua.ck.zabochen.englishverbs.R
import ua.ck.zabochen.englishverbs.utils.Constants
import ua.ck.zabochen.englishverbs.ui.verbfull.VerbFullActivity

class NotificationHelper {

    fun createNotification(context: Context) {

        // Builder
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setAutoCancel(true)

        // Intent
        val intent: Intent = Intent(context, VerbFullActivity::class.java)

        // Intents Stack
        val stack: TaskStackBuilder = TaskStackBuilder.create(context)
        stack.addParentStack(VerbFullActivity::class.java)
        stack.addNextIntent(intent)

        // Pending Intent from Stack
        val pendingIntent: PendingIntent? = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationBuilder.setContentIntent(pendingIntent)

        // Notification
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(Constants.NOTIFICATION_ID, notificationBuilder.build())

    }

}