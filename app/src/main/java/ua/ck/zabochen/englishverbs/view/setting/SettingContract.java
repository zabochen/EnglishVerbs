package ua.ck.zabochen.englishverbs.view.setting;

import ua.ck.zabochen.englishverbs.mvp.presenter.MvpPresenter;
import ua.ck.zabochen.englishverbs.mvp.view.MvpView;

public class SettingContract {

    public interface View extends MvpView {

    }

    public interface Presenter extends MvpPresenter<SettingContract.View> {
        void sharedPreferenceChanged();
    }

}
