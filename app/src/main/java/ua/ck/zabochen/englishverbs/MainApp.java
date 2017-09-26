package ua.ck.zabochen.englishverbs;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ua.ck.zabochen.englishverbs.dagger.AppComponent;
import ua.ck.zabochen.englishverbs.dagger.AppModule;
import ua.ck.zabochen.englishverbs.dagger.DaggerAppComponent;

public class MainApp extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setRealm();
        setDagger();
    }

    private void setRealm() {
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("english_verb.realm")
                .schemaVersion(1)
                .build()
        );
    }

    private void setDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
