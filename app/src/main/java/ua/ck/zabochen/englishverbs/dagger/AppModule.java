package ua.ck.zabochen.englishverbs.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ua.ck.zabochen.englishverbs.database.RealmHelper;
import ua.ck.zabochen.englishverbs.model.VerbList;
import ua.ck.zabochen.englishverbs.notification.NotificationHelper;
import ua.ck.zabochen.englishverbs.view.setting.SettingFragment;
import ua.ck.zabochen.englishverbs.view.setting.SettingPresenter;
import ua.ck.zabochen.englishverbs.view.verb.VerbFragment;
import ua.ck.zabochen.englishverbs.view.verb.VerbPresenter;
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullActivity;
import ua.ck.zabochen.englishverbs.view.verbfull.VerbFullPresenter;

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

    // VerbList Singleton
    @Provides
    @Singleton
    public VerbList provideVerbList() {
        return new VerbList();
    }

    @Provides
    @Singleton
    public VerbFragment provideVerbFragment() {
        return new VerbFragment();
    }

    @Provides
    @Singleton
    public VerbPresenter provideVerbPresenter() {
        return new VerbPresenter();
    }

    @Provides
    @Singleton
    public VerbFullActivity provideVerbFullActivity() {
        return new VerbFullActivity();
    }

    @Provides
    @Singleton
    public VerbFullPresenter provideVerbFullPresenter() {
        return new VerbFullPresenter();
    }

    @Provides
    @Singleton
    public SettingPresenter provideSettingPresenter() {
        return new SettingPresenter();
    }

    @Provides
    @Singleton
    public SettingFragment provideSettingFragmnet() {
        return new SettingFragment();
    }

}
