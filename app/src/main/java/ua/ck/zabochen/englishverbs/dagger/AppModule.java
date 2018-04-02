package ua.ck.zabochen.englishverbs.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.ck.zabochen.englishverbs.helper.database.RealmHelper;
import ua.ck.zabochen.englishverbs.helper.notification.NotificationHelper;
import ua.ck.zabochen.englishverbs.helper.speech.SpeechHelper;

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

    // TextToSpeech
    @Provides
    @Singleton
    public SpeechHelper provideSpeechHelper(Context context) {
        return new SpeechHelper(context);
    }

    // Notification
    @Provides
    @Singleton
    public NotificationHelper provideNotificationHelper() {
        return new NotificationHelper();
    }

}
