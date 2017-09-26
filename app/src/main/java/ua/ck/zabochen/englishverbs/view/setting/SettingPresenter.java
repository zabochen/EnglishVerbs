package ua.ck.zabochen.englishverbs.view.setting;

import javax.inject.Inject;

import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.database.RealmHelper;
import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.mvp.presenter.BasePresenter;
import ua.ck.zabochen.englishverbs.notification.NotificationHelper;

public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    @Inject RealmHelper mRealmHelper;
    @Inject NotificationHelper mNotificationHelper;
    @Inject SettingFragment mSettingFragment;

    public SettingPresenter() {
        MainApp.getAppComponent().inject(this);
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void sharedPreferenceChanged() {
        Verb verb = mRealmHelper.getRandomVerb();
        mNotificationHelper.createNotification(verb.get);
    }
}
