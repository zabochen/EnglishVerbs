package ua.ck.zabochen.englishverbs.view.main;

import ua.ck.zabochen.englishverbs.mvp.presenter.MvpPresenter;
import ua.ck.zabochen.englishverbs.mvp.view.MvpView;

public class MainContract {

    public interface View extends MvpView {

    }

    public interface Presenter extends MvpPresenter<View> {

    }

}
