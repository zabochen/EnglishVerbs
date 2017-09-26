package ua.ck.zabochen.englishverbs.mvp.presenter;

import ua.ck.zabochen.englishverbs.mvp.view.MvpView;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void viewIsReady();

    void detachView();

    void destroy();

}
