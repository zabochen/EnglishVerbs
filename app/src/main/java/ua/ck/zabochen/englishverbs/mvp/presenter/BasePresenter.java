package ua.ck.zabochen.englishverbs.mvp.presenter;

import ua.ck.zabochen.englishverbs.mvp.view.MvpView;

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void destroy() {

    }

}
