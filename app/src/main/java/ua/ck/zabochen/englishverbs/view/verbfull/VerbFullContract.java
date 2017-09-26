package ua.ck.zabochen.englishverbs.view.verbfull;

import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.mvp.presenter.MvpPresenter;
import ua.ck.zabochen.englishverbs.mvp.view.MvpView;

public class VerbFullContract {

    public interface View extends MvpView {
        void updateUi(Verb verb);
    }

    public interface Presenter extends MvpPresenter<View> {
        void verbPosition(int position);
        void createNotification(int position);
    }
}
