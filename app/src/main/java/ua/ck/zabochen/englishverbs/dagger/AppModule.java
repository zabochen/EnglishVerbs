package ua.ck.zabochen.englishverbs.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.ck.zabochen.englishverbs.database.RealmHelper;
import ua.ck.zabochen.englishverbs.notification.NotificationHelper;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

    // Realm
    @Provides
    @Singleton
    public RealmHelper provideRealmHelper(Context context) {
        return new RealmHelper(context);
    }

    // Notification
    @Provides
    @Singleton
    public NotificationHelper provideNotificationHelper(Context context) {
        return new NotificationHelper(context);
    }

}
