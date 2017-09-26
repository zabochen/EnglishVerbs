package ua.ck.zabochen.englishverbs.view.verbfull;

import javax.inject.Inject;

import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.database.RealmHelper;
import ua.ck.zabochen.englishverbs.mvp.presenter.BasePresenter;
import ua.ck.zabochen.englishverbs.notification.NotificationHelper;

public class VerbFullPresenter extends BasePresenter<VerbFullContract.View> implements VerbFullContract.Presenter {

    @Inject RealmHelper mRealmHelper;
    @Inject NotificationHelper mNotificationHelper;

    public VerbFullPresenter() {
        MainApp.getAppComponent().inject(this);
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void verbPosition(int position) {
        getView().updateUi(mRealmHelper.getVerbList().get(position));
    }

    @Override
    public void createNotification(int position) {
        mNotificationHelper.createNotification(
                position,
                mRealmHelper.getVerbList().get(position)
        );
    }

}
