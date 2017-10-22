package ua.ck.zabochen.englishverbs.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.model.realm.Verb;

public class NotificationHelper {

    private Context mContext;

    public NotificationHelper(Context context) {
        this.mContext = context;
    }

    public void createNotification(int verbPosition, Verb verb) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "channel_Id")
                .setSmallIcon(R.drawable.no_image)
                .setContentTitle(verb.getVerb())
                .setContentText(verb.getVerbTranscription())
                .setAutoCancel(true);
//
//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(mContext);
//        taskStackBuilder.addParentStack(VerbFullActivity.class);
//
//        Intent intent = new Intent(mContext);
//        intent.putExtra("position", verbPosition);
//        taskStackBuilder.addNextIntent(intent);

//        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

//        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

}
